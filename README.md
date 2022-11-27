# rustorepush

Flutter RuStore push SDK.




flutter pub run pigeon \
--input pigeons/rustore.dart \
--dart_out lib/pigeons/rustore.dart \
--java_out ./android/src/main/kotlin/ru/rustore/pushsdk/pigeons/RustorePush.java \
--java_package "ru.rustore.pushsdk.pigeons"


adb install build/app/outputs/flutter-apk/app-release.apk
