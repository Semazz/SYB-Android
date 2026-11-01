package com.sybbox.data.remote

/** Strict SemVer precedence, including numeric prerelease identifiers. */
data class AppVersion(val major: Int, val minor: Int, val patch: Int, val prerelease: List<String>) : Comparable<AppVersion> {
    override fun compareTo(other: AppVersion): Int {
        compareValuesBy(this, other, AppVersion::major, AppVersion::minor, AppVersion::patch)
            .takeIf { it != 0 }?.let { return it }
        if (prerelease.isEmpty() != other.prerelease.isEmpty()) return if (prerelease.isEmpty()) 1 else -1
        for (index in 0 until minOf(prerelease.size, other.prerelease.size)) {
            val a = prerelease[index]
            val b = other.prerelease[index]
            val an = a.toLongOrNull()
            val bn = b.toLongOrNull()
            val result = when {
                an != null && bn != null -> an.compareTo(bn)
                an != null -> -1
                bn != null -> 1
                else -> a.compareTo(b)
            }
            if (result != 0) return result
        }
        return prerelease.size.compareTo(other.prerelease.size)
    }
    companion object {
        private val pattern = Regex("^(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)(?:-([0-9A-Za-z-]+(?:\\.[0-9A-Za-z-]+)*))?(?:\\+([0-9A-Za-z-]+(?:\\.[0-9A-Za-z-]+)*))?$")
        fun normalize(value: String) = value.removePrefix("android-").removePrefix("v")
        fun parse(value: String): AppVersion? = runCatching {
            val match = pattern.matchEntire(normalize(value)) ?: return null
            val pre = match.groupValues[4].split('.').filter { it.isNotEmpty() }
            if (pre.any { it.all(Char::isDigit) && (it.length > 1 && it.startsWith('0') || it.toLongOrNull() == null) }) return null
            AppVersion(match.groupValues[1].toInt(), match.groupValues[2].toInt(), match.groupValues[3].toInt(), pre)
        }.getOrNull()
    }
}
