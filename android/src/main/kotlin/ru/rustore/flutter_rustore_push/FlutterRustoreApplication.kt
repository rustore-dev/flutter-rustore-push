package ru.rustore.flutter_rustore_push

import android.os.Bundle
import android.os.PersistableBundle
import io.flutter.app.FlutterApplication

open class FlutterRustoreApplication: FlutterApplication() {
    override fun onCreate() {
        FlutterRustorePushClient.initialization(this)
        super.onCreate()
    }
}