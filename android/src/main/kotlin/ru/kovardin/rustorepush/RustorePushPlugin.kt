package ru.kovardin.rustorepush

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import ru.kovardin.rustorepush.pigeons.RustorePush

/** RustorePushPlugin */
class RustorePushPlugin : FlutterPlugin {
    private lateinit var context: Context
    private lateinit var application: Application

    override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        context = binding.applicationContext

        Log.w(
            "RustorePushPlugin",
            "Trying to resolve Application from Context: ${context.javaClass.name}"
        )
        application = context as Application


        val rustore = RustorePushClient(application)
        RustorePush.PushClient.setup(binding.binaryMessenger, rustore)
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }
}
