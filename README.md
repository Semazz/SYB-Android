<div align="center">

![SYB](docs/images/brand.svg)

**Клиент для Android на ядре SYBcore.**

[![Release](https://img.shields.io/github/v/release/Semazz/SYB-Android?style=flat-square&color=d9ff57&labelColor=151914&label=SYB)](https://github.com/Semazz/SYB-Android/releases/latest)
[![Android 7+](https://img.shields.io/badge/Android-7.0%2B-d9ff57?style=flat-square&labelColor=151914)](https://developer.android.com/about/versions/nougat)
[![Xray 26.10.31](https://img.shields.io/badge/Xray-26.10.31-aeb9a0?style=flat-square&labelColor=151914)](https://github.com/XTLS/Xray-core)
[![GPL-3.0](https://img.shields.io/badge/license-GPL--3.0-aeb9a0?style=flat-square&labelColor=151914)](LICENSE)

</div>

## Возможности

- **Протоколы** - VLESS, VMess, Trojan, Shadowsocks, Hysteria2, WireGuard, SOCKS, HTTP, REALITY, XHTTP. Hysteria2 с обфускацией salamander и прыжками портов, ECH и закрепление сертификата для TLS.
- **JSON-конфиги** - серверы из JSON-подписок запускаются ровно так, как их написал провайдер: несколько выходов, балансировщики, цепочки, MASQUE, XDRIVE.
- **Подписки** - ссылка, QR-код, файл или буфер обмена; обновление по расписанию, трафик и срок, объявления и поддержка провайдера.
- **Маршрутизация провайдера** - профили в формате Happ и INCY: DNS-записи, прямые и заблокированные сайты, серверы обхода белых списков.
- **Свои списки** - «Напрямую», «Через прокси» и «Блокировать», как в Happ: сайты, адреса и категории по одному на строку, готовые наборы.
- **Российские сайты напрямую** - одним переключателем, .ru-сайты видят обычный IP.
- **SYBping** - точный пинг без учёта DNS, нажмите на цифры у сервера, чтобы перемерить.
- **Скорость** - подключение за доли секунды, настройки применяются сразу без переподключения, SYBcore на Xray 26.10.31.
- **Защита** - блокировка рекламы и трекеров, контроль IPv6 и STUN, выбор приложений для туннеля.
- **Удобство** - виджеты в цветах темы, плитка в шторке, автозапуск, обновления прямо из приложения: новая версия находится за 20 минут.

Языки: русский, English, Español, 简体中文.

## Установка

Скачайте файл для своего телефона из [последнего релиза](https://github.com/Semazz/SYB-Android/releases/latest):

| Устройство | Файл |
|---|---|
| Почти все современные телефоны | [**SYB-1.0.5-arm64-v8a.apk**](https://github.com/Semazz/SYB-Android/releases/latest/download/SYB-1.0.5-arm64-v8a.apk) |
| Старые 32-битные телефоны | [SYB-1.0.5-armeabi-v7a.apk](https://github.com/Semazz/SYB-Android/releases/latest/download/SYB-1.0.5-armeabi-v7a.apk) |
| Эмуляторы и устройства на Intel | [SYB-1.0.5-x86_64.apk](https://github.com/Semazz/SYB-Android/releases/latest/download/SYB-1.0.5-x86_64.apk) |

Не знаете, какой нужен - берите первый. Дальше SYB обновляется сам, прямо в приложении.

SYB не продаёт и не раздаёт серверы - нужна собственная подписка или конфигурация. Ключи и ссылки подписок хранятся зашифрованными (Android Keystore).

## Лицензии

SYB - [GPL-3.0](LICENSE). [Xray-core](https://github.com/XTLS/Xray-core) - MPL-2.0, флаги - [flag-icons](https://github.com/lipis/flag-icons) (MIT), отрисовка SVG - [AndroidSVG](https://bigbadaboom.github.io/androidsvg/) (Apache-2.0). Тексты лицензий - в [docs/licenses](docs/licenses).

<div align="center"><sub>Сообщая о проблеме, укажите версию, модель телефона и протокол. Не публикуйте ссылки подписок, UUID и ключи.</sub></div>
