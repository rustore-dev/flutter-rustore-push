# flutter_rustore_push

Flutter RuStore push SDK для подключения пуш уведомлений.

##[Документация RuStore](https://help.rustore.ru/rustore/for_developers/developer-documentation/sdk_push-notifications/flutter_push-notifications)

## Общее

## Пример реализации

Для того, чтобы узнать как правильно интегрировать пакет для работы с push-уведомлениями, рекомендуется ознакомиться с [приложением-примером](https://gitflic.ru/project/rustore/flutter-rustore-push/file?file=example)

## Необходимые условия

Для работы push-уведомлений необходимо соблюдение следующих условий:

- На устройстве пользователя должен быть установлен RuStore. 
- RuStore должен поддерживать функциональность push-уведомлений. 
- Приложению RuStore разрешён доступ к работе в фоновом режиме. 
- Пользователь должен быть авторизован в RuStore. 
- Подключение в проект
- Для подключения пакета к проекту нужно выполнить команду

```
flutter pub add flutter_rustore_push
```

Эта команда добавит строчку в файл pubspec.yaml

```
dependencies:
  flutter_rustore_push: ^6.3.1
```

## Инициализация

Для инициализации сервиса пуш-уведомлений необходимо добавить значение в AndroidManifest вашего проекта.

```kotlin
<meta-data
    android:name="ru.rustore.sdk.pushclient.project_id"
    android:value="your_project_id_from_rustore_console" />
```

## Настройки ProGuard

Для настройки ProGuard вам необходимо добавить следующее правило:

```
-keep public class com.vk.push.** extends android.os.Parcelable
```

И в файле android/app/build.gradle добавьте:

```
buildTypes {
    release {
        // ...

        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
    }

    // ...
}
```

## Проверка возможности получения push-уведомления

Для работы push-уведомлений необходимо соблюдать несколько условий:

- На устройстве пользователя должен быть установлен RuStore. 
- RuStore должен поддерживать функциональность push-уведомлений.
- Приложению RuStore разрешён доступ к работе в фоновом режиме.   
- Пользователь должен быть авторизован в RuStore. 
- Для проверки вышеперечисленных условий можно воспользоваться методом `RustorePushClient.available()`

```
RustorePushClient.available().then((value) {
      print("available success: ${value}");
}, onError: (err) {
      print("available error: ${err}");
});
```

## Методы для работы с push-токеном и сообщением

### Получение push-токена пользователя

После инициализации библиотеки вы можете использовать метод `RustorePushClient.getToken()`, для получения текущего push-токена пользователя. Если у пользователя отсутствует push-токен, то метод создаст и вернёт новый push-токен . 

```
RustorePushClient.getToken().then((value) {
      print("get token success: ${value}");
}, onError: (err) {
      print("get token error: ${err}");
})
```

### Удаление push-токена пользователя

Вы можете использовать метод `RustorePushClient.deleteToken()`, для удаления текущего push-токена пользователя.

```
RustorePushClient.deleteToken().then(() {
      print("delete success:");
}, onError: (err) {
      print("delete error: ${err}");
})
```

### События изменения токена

Периодически старый токен может становиться невалидным. Токен может выписываться заново. Чтобы понять, что выписался новый токен, нужно использовать коллбек `RustorePushClient.onNewToken()`

```
RustorePushClient.onNewToken((value) {
      print("on new token success: ${value}");
}, error: (err) {
      print("on new token err: ${err}");
});
```

### Работа с push-сообщением

Для получения информации из push-уведомления необходимо добавить коллбек `RustorePushClient.onMessageReceived()`

```
RustorePushClient.onMessageReceived((value) {
      print("on message received success: id=${value.messageId}, data=${value.data}, notification.body: ${value.notification?.body}");
}, error: (err) {
      print("on message received error: ${err}");
});
```

### Удаление push-сообщения

Для удвления push-уведомления необходимо добавить коллбек `RustorePushClient.onDeletedMessages()`

RustorePushClient.onDeletedMessages(() {
      print("deleted messages");
}, error: (err) {
      print("on message received error: ${err}");
});

## Обработка ошибок

Для обработки ошибок необходимо использовать коллбек `RustorePushClient.onError()`

```
RustorePushClient.onError((err) {
      print("on error: ${err}");
});
```

## Структура уведомления

Структура `Message`:

```
class Message {
  String? messageId;
  int priority;
  int ttl;
  String? collapseKey;
  Map<String?, String?> data;
  Notification? notification;
}
```

- messageId - уникальный ID сообщения. Является идентификатором каждого сообщения. 
- priority - (на данный момент не учитывается) возвращает значение приоритетности. Сейчас заложены следующие варианты: 
    - 0 - UNKNOWN. 
    - 1 - HIGH. 
    - 2 - NORMAL. 
- ttl - время жизни push-уведомления типа Int в секундах. 
- collapseKey - (на данный момент не учитывается) идентификатор группы уведомлений. 
- data - словарь, в который можно передать дополнительные данные для уведомления. 
- notification - объект уведомления. 

Структура `Notification`:

```
class Notification {
  String? title;
  String? body;
  String? channelId;
  String? imageUrl;
  String? color;
  String? icon;
  String? clickAction;
}
```

- title - заголовок уведомления. 
- body - тело уведомления. 
- channelId - возможность задать канал, в который будет отправлено уведомление (актуально для Android 8.0 и выше).
- imageUrl - прямая ссылка на изображение для вставки в уведомление (изображение должно быть не более 1 мегабайта). 
- color - цвет уведомления (Notification.color). Цвет необходимо передать в hex-формате, строкой (Пример: “#A52A2A”).
- icon - иконка уведомления. Иконка должна лежать в ресурсах приложения (res/drawable). Значение параметра - строка, которая совпадает с названием ресурса.
    - Пример: в res/drawable лежит иконка small_icon.xml, которая в коде доступна через R.drawable.small_icon. Для отображения данной иконки в уведомлении сервер должен поместить в параметр “icon” значение “small_icon”. 
- clickAction - intent action, с помощью которого будет открыта активити при клике на уведомление.
