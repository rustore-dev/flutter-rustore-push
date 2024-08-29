package ru.rustore.flutter_rustore_push

import android.content.Context
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import ru.rustore.flutter_rustore_push.utils.Log
import ru.rustore.flutter_rustore_push.pigeons.RuStorePush
import ru.rustore.flutter_rustore_push.pigeons.RuStorePushCallbacks

/** RustorePushPlugin */
class FlutterRustorePushPlugin : FlutterPlugin {
    private lateinit var context: Context

    override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        context = binding.applicationContext

        Log.d(
            "Trying to resolve Application from Context: ${context.javaClass.name}"
        )

        val rustore = FlutterRustorePushClient()
        val callbacks = RuStorePushCallbacks(binding.binaryMessenger)

        FlutterRustorePushService.client = callbacks

        RuStorePush.setUp(binding.binaryMessenger, rustore)

    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }
}
