package ru.rustore.flutter_rustore_push.utils

import android.util.Log as l

object Log {
    fun i(msg: String) {
        l.i("FlutterRustorePush", msg)
    }

    fun d(msg: String) {
        l.d("FlutterRustorePush", msg)
    }
}