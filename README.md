# flutter_rustore_push

Flutter RuStore push SDK для подключения пуш уведомлений.

Документация по настройки [RuStore SDK для подключения пуш-уведомлений](https://help.rustore.ru/rustore/for_developers/developer-documentation/RuStore-SDK-push-notifications).

Для работы пуш-уведомлений необходимо соблюдение следующих условий:

- На устройстве пользователя должно быть установлено приложение RuStore.
- Приложение RuStore должно поддерживать функциональность пуш-уведомлений.
- Приложению RuStore разрешён доступ к работе в фоновом режиме.
- Пользователь должен быть авторизован в приложении RuStore.

## Инициализация

Для инициализации сервиса работы с пуш-уведомлениями необходимо добавить значение в values вашего android проекта.

```
<resources>
    <string name="flutter_rustore_push_project" translatable="false">XXXXXXXXXXX</string>
</resources>
```

flutter_rustore_push_project - идентификатор проекта пуш-уведомлений из консоли разработчика.

И необходимо добавить класс Application наследованный от FlutterRustoreApplication

```
package ru.rustore.flutter_rustore_push_example

import ru.rustore.flutter_rustore_push.FlutterRustoreApplication

open class Application: FlutterRustoreApplication() {
}
```

И в манифесте приложения указать этот класс

```
 <application
        android:label="flutter_rustore_push_example"
        android:name=".Application"
        android:icon="@mipmap/ic_launcher">
        // ...
 </application>
```

### ProGuard

Для работы приложения в релизной сборке необходимо указать правила для proguard. Для этого создайте файл android/app/proguard-rules.pro с содержимым:

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
