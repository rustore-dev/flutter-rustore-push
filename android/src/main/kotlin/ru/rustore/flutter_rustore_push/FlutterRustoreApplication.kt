package ru.rustore.flutter_rustore_push

import io.flutter.app.FlutterApplication
import ru.rustore.flutter_rustore_push.utils.Resource

open class FlutterRustoreApplication: FlutterApplication() {
    override fun onCreate() {
//        FlutterRustorePushClient.initialization(this)
        super.onCreate()
    }
}