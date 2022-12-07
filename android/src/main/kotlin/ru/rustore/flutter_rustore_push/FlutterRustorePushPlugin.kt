package ru.rustore.flutter_rustore_push

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import ru.rustore.flutter_rustore_push.pigeons.Rustore

/** RustorePushPlugin */
class FlutterRustorePushPlugin : FlutterPlugin {
    private lateinit var context: Context
    private lateinit var application: Application

    override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        context = binding.applicationContext

        Log.w(
            "RustorePushPlugin",
            "Trying to resolve Application from Context: ${context.javaClass.name}"
        )
        application = context as Application


        val rustore = FlutterRustorePushClient(application)
        Rustore.Client.setup(binding.binaryMessenger, rustore)
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }
}
