package com.sybbox.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.sybbox.domain.model.ServerProfile
import com.sybbox.domain.model.ConnectionState
import com.sybbox.service.SybBoxVpnService
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

enum class MeasurementMode { SYB, TCP, DNS, HTTPS, HTTPS_HEAD, ICMP;
    val displayName: String get() = when(this) { SYB -> "SYBping"; TCP -> "TCP"; DNS -> "DNS"; ICMP -> "ICMP"; HTTPS -> "HTTPS GET · Proxy"; HTTPS_HEAD -> "HTTPS HEAD · Proxy" }
    val throughTunnel: Boolean get() = this == HTTPS || this == HTTPS_HEAD
}

object NetworkMeasurement {
    private const val SYB_ROUNDS = 4

    /** SYBping result: the first handshake warms routes/ARP and is dropped, the median of the rest is reported. */
    internal fun stableMillis(samples: List<Int>): Int {
        val usable = (if (samples.size >= 3) samples.drop(1) else samples).filter { it > 0 }.sorted()
        if (usable.isEmpty()) return -1
        val mid = usable.size / 2
        return if (usable.size % 2 == 1) usable[mid] else (usable[mid - 1] + usable[mid]) / 2
    }

    /** TCP/DNS use the physical network; HTTPS uses the core probe inbound, since this app is excluded from the TUN. */
    suspend fun sample(context: Context, mode: MeasurementMode, profile: ServerProfile?, url: String = "https://www.gstatic.com/generate_204", timeoutSeconds: Int = 3): Int =
        withTimeoutOrNull(timeoutSeconds.coerceIn(1, 10) * 1_000L + 1_000) {
            withContext(Dispatchers.IO) {
                runCatching {
                    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val network = cm.allNetworks.firstOrNull { network ->
                        val caps = cm.getNetworkCapabilities(network) ?: return@firstOrNull false
                        caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                            !caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
                    }
                    val target = profile ?: return@runCatching -1
                    val host = target.address.takeIf { it.isNotBlank() } ?: return@runCatching -1
                    val start = System.nanoTime()
                    when (mode) {
                        MeasurementMode.SYB -> {
                            // DNS is resolved once and kept out of the figure; only TCP handshakes are timed.
                            val address = network?.getAllByName(host)?.firstOrNull() ?: return@runCatching -1
                            val perRound = (timeoutSeconds.coerceIn(1, 10) * 1_000 / SYB_ROUNDS).coerceAtLeast(400)
                            val samples = (1..SYB_ROUNDS).map {
                                runCatching {
                                    val t = System.nanoTime()
                                    network.socketFactory.createSocket().use { s -> s.tcpNoDelay = true; s.connect(InetSocketAddress(address, target.port), perRound) }
                                    ((System.nanoTime() - t) / 1_000_000).toInt().coerceAtLeast(1)
                                }.getOrDefault(-1)
                            }
                            return@runCatching stableMillis(samples)
                        }
                        MeasurementMode.DNS -> network?.getAllByName(host)?.also { require(it.isNotEmpty()) }
                            ?: return@runCatching -1
                        MeasurementMode.TCP -> {
                            val address = network?.getAllByName(host)?.firstOrNull() ?: return@runCatching -1
                            val tcpStart = System.nanoTime()
                            network.socketFactory.createSocket().use { it.connect(InetSocketAddress(address, target.port), timeoutSeconds.coerceIn(1, 10) * 1_000) }
                            return@runCatching ((System.nanoTime() - tcpStart) / 1_000_000).toInt().coerceAtLeast(1)
                        }
                        MeasurementMode.ICMP -> {
                            // The child inherits the app UID, which is excluded from the VPN.
                            val address = network?.getAllByName(host)?.firstOrNull()?.hostAddress ?: return@runCatching -1
                            val process = ProcessBuilder("/system/bin/ping", "-n", "-c", "1", "-W", timeoutSeconds.coerceIn(1, 10).toString(), address)
                                .redirectErrorStream(true).start()
                            try {
                                val output = process.inputStream.bufferedReader().use { it.readText() }
                                if (process.waitFor() != 0) return@runCatching -1
                                return@runCatching ProbeTarget.icmpMillis(output) ?: -1
                            } finally { process.destroy() }
                        }
                        MeasurementMode.HTTPS, MeasurementMode.HTTPS_HEAD -> {
                            val port = SybBoxVpnService.diagnosticProxyPort
                            if (SybBoxVpnService.appState.value.connectionState != ConnectionState.CONNECTED || SybBoxVpnService.appState.value.activeProfile?.id != target.id || port !in 1..65535) return@runCatching -1
                            val client = OkHttpClient.Builder()
                                .proxy(Proxy(Proxy.Type.SOCKS, InetSocketAddress("127.0.0.1", port)))
                                .connectionPool(ConnectionPool(0, 1, TimeUnit.SECONDS))
                                .connectTimeout(timeoutSeconds.coerceIn(1, 10).toLong(), TimeUnit.SECONDS).callTimeout(timeoutSeconds.coerceIn(1, 10).toLong(), TimeUnit.SECONDS)
                                .retryOnConnectionFailure(false).followRedirects(false).followSslRedirects(false).build()
                            try {
                                require(ProbeTarget.validUrl(url))
                                val request = Request.Builder().url(url).apply { if (mode == MeasurementMode.HTTPS_HEAD) head() }.build()
                                client.newCall(request).execute().use {
                                    require(it.code in 200..399)
                                    require(SybBoxVpnService.appState.value.connectionState == ConnectionState.CONNECTED &&
                                        SybBoxVpnService.appState.value.activeProfile?.id == target.id && SybBoxVpnService.diagnosticProxyPort == port)
                                }
                            } finally {
                                client.connectionPool.evictAll()
                                client.dispatcher.executorService.shutdown()
                            }
                        }
                    }
                    ((System.nanoTime() - start) / 1_000_000).toInt().coerceAtLeast(1)
                }.getOrDefault(-1)
            }
        } ?: -1
}
