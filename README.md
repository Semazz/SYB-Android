<div align="center">

![SYB](docs/images/brand.svg)

**Быстрый клиент для ваших серверов на ядре SYBcore ([Xray-core](https://github.com/XTLS/Xray-core)).**

[![Release](https://img.shields.io/github/v/release/Semazz/SYB-Android?style=flat-square&color=d9ff57&labelColor=151914&label=SYB)](https://github.com/Semazz/SYB-Android/releases/latest)
[![Downloads](https://img.shields.io/github/downloads/Semazz/SYB-Android/total?style=flat-square&color=aeb9a0&labelColor=151914&label=downloads)](https://github.com/Semazz/SYB-Android/releases)
[![Android](https://img.shields.io/badge/Android-7.0%2B-d9ff57?style=flat-square&logo=android&logoColor=white&labelColor=151914)](#скачать)
[![Android TV](https://img.shields.io/badge/Android_TV-ready-d9ff57?style=flat-square&logo=androidtv&logoColor=white&labelColor=151914)](#скачать)
[![SYBcore](https://img.shields.io/badge/SYBcore-1.0.1-aeb9a0?style=flat-square&labelColor=151914)](https://github.com/XTLS/Xray-core)
[![Issues](https://img.shields.io/badge/report-issues-aeb9a0?style=flat-square&logo=github&labelColor=151914)](https://github.com/Semazz/SYB-Android/issues)

</div>

## Скачать

| Платформа | Где взять |
|---|---|
| Android 7+ | [**SYB-1.0.6-arm64-v8a.apk**](https://github.com/Semazz/SYB-Android/releases/latest/download/SYB-1.0.6-arm64-v8a.apk) |
| Android TV и старые телефоны | [SYB-1.0.6-armeabi-v7a.apk](https://github.com/Semazz/SYB-Android/releases/latest/download/SYB-1.0.6-armeabi-v7a.apk) |
| Эмуляторы и устройства на Intel | [SYB-1.0.6-x86_64.apk](https://github.com/Semazz/SYB-Android/releases/latest/download/SYB-1.0.6-x86_64.apk) |

Не знаете, какой файл нужен - берите первый. Дальше SYB обновляется сам, прямо в приложении. Подписку на телевизор проще всего передать с телефона: откройте на ТВ «Добавить с телефона» и наведите на QR-код камеру в SYB на телефоне.

## Обновления через Obtainium

SYB для Android обновляется сам. Если удобнее держать все приложения в [Obtainium](https://github.com/ImranR98/Obtainium), [импортируйте настройки](obtainium.json) (Добавить приложение → Импорт) или добавьте вручную:

| Настройка | Значение |
|---|---|
| Источник | `https://github.com/Semazz/SYB-Android` |
| Фильтр APK | `arm64-v8a` (для ТВ и старых телефонов `armeabi-v7a`) |
| Предварительные выпуски | выключено |

## Протоколы

`VLESS` `VMess` `Trojan` `Shadowsocks` `Hysteria2` `TUIC` `WireGuard` `SOCKS` `HTTP` `REALITY` `XHTTP` `gRPC` `WebSocket`

## Возможности

| Возможность | Телефон | ТВ |
|---|:-:|:-:|
| **Подписки** - ссылка, QR-код, файл, буфер обмена, полные JSON-конфиги | ✓ | ✓ |
| **С телефона на ТВ** - подписки, серверы и настройки по QR-коду | ✓ | ✓ |
| **Маршруты** - свои списки, российские сайты и локальная сеть напрямую | ✓ | ✓ |
| **Выбор приложений** - что идёт через туннель, а что нет | ✓ | ✓ |
| **SYBping** - точный пинг и проверка, что сервер пропускает трафик | ✓ | ✓ |
| **Защита** - блокировка трекеров, контроль утечек IP и WebRTC | ✓ | ✓ |
| **Темы и значки** - SYBcreate, SYBgroup, EVA-01 и другие | ✓ | ✓ |
| **Управление пультом** - боковое меню и крупные элементы | - | ✓ |
| **Виджеты и плитка в шторке** | ✓ | - |
| **Автоматизация** - `sybbox://connect`, `disconnect`, `toggle` | ✓ | ✓ |
| **Резервная копия** - все настройки одним файлом | ✓ | ✓ |
| **Без аккаунтов, рекламы и аналитики** | ✓ | ✓ |

Языки: русский, English, Español, 简体中文.

## Приватность

SYB не собирает данные и ничего не отправляет разработчику. Подробно - в [политике конфиденциальности](docs/privacy.md).

## Лицензии

SYB - [GPL-3.0](LICENSE). [Xray-core](https://github.com/XTLS/Xray-core) - MPL-2.0, флаги - [flag-icons](https://github.com/lipis/flag-icons) (MIT), отрисовка SVG - [AndroidSVG](https://bigbadaboom.github.io/androidsvg/) (Apache-2.0). Тексты лицензий - в [docs/licenses](docs/licenses).

<div align="center"><sub>SYB - клиент для ваших собственных серверов: он не продаёт и не раздаёт серверы, нужна своя подписка или конфигурация. Соблюдайте законы своей страны.</sub></div>
