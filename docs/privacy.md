# Политика конфиденциальности SYB

Действует с 7 ноября 2026 года. Разработчик: SYBcreate.

SYB - клиент для серверов, которые вы добавляете сами. У приложения нет аккаунтов, рекламы и аналитики, и оно не отправляет разработчику никаких данных.

## Что хранится на устройстве

Серверы, подписки, настройки и журналы хранятся только на вашем устройстве. Ключи и ссылки подписок зашифрованы с помощью Android Keystore. Журналы по умолчанию удаляются через час. При удалении приложения всё это удаляется.

## Что и куда отправляется

- **Ваш трафик** при подключении идёт только на сервер, который вы выбрали. Разработчик его не видит.
- **Провайдеру подписки** (по адресу, который вы добавили) при загрузке и обновлении подписки отправляются: название и версия приложения (User-Agent), случайный идентификатор устройства, который SYB создаёт сам и который не связан с вашим телефоном, версия Android и модель устройства. Это нужно провайдеру, чтобы считать подключённые устройства. Что провайдер делает с этими данными, определяет его собственная политика.
- **Проверка соединения и пинг** обращаются к выбранным серверам и к адресу проверки (по умолчанию `www.gstatic.com`).
- **Версия с GitHub** проверяет наличие обновлений на `api.github.com`. Версия из Google Play этого не делает.
- **Передача на ТВ** идёт напрямую по домашней сети, а если это невозможно - через ретранслятор ntfy.sh в зашифрованном виде. Ключ шифрования есть только в QR-коде на экране ТВ.

## Разрешения

- **VPN** - чтобы направлять трафик через выбранный сервер.
- **Камера** - только для сканирования QR-кодов. Снимки не сохраняются и никуда не отправляются.
- **Уведомления** - состояние подключения и, в версии с GitHub, сообщения о новой версии.
- **Запуск при загрузке** - для автоподключения, если вы его включили.

## Дети

SYB не предназначен для детей младше 13 лет и сознательно не собирает данные детей.

## Изменения и связь

Изменения этой политики публикуются на этой странице. Вопросы - в [Issues](https://github.com/Semazz/SYB-Android/issues).

---

# SYB Privacy Policy

Effective November 7, 2026. Developer: SYBcreate.

SYB is a client for servers that you add yourself. The app has no accounts, ads or analytics, and sends no data to the developer.

## Stored on your device

Servers, subscriptions, settings and logs stay on your device only. Keys and subscription links are encrypted with Android Keystore. Logs are deleted after one hour by default. Uninstalling the app removes all of it.

## What is sent, and where

- **Your traffic**, while connected, goes only to the server you chose. The developer never sees it.
- **Your subscription provider** (the address you added) receives, when the subscription loads or updates: the app name and version (User-Agent), a random device identifier that SYB creates itself and that is not tied to your phone, the Android version and the device model. Providers use this to count connected devices; what they do with it is governed by their own policy.
- **Connection checks and ping** contact your servers and the check address (`www.gstatic.com` by default).
- **The GitHub version** checks `api.github.com` for updates. The Google Play version does not.
- **Sending to a TV** goes directly over your home network or, if that is not possible, through the ntfy.sh relay in encrypted form. The key exists only in the QR code on the TV screen.

## Permissions

- **VPN** - to route traffic through the server you chose.
- **Camera** - only to scan QR codes. Images are never stored or sent.
- **Notifications** - connection status and, in the GitHub version, new version alerts.
- **Run at startup** - for auto-connect, if you turn it on.

## Children

SYB is not directed at children under 13 and does not knowingly collect children's data.

## Changes and contact

Changes to this policy are published on this page. Questions: [Issues](https://github.com/Semazz/SYB-Android/issues).
