package ru.rustore.flutter_rustore_push

import android.content.Intent
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.*
import io.flutter.plugin.common.PluginRegistry.NewIntentListener
import ru.rustore.flutter_rustore_push.pigeons.RuStorePush
import ru.rustore.flutter_rustore_push.pigeons.RuStorePushCallbacks
import ru.rustore.flutter_rustore_push.utils.Log


/** RustorePushPlugin */
class FlutterRustorePushPlugin : FlutterPlugin, ActivityAware, NewIntentListener {
    private lateinit var flutterBinding: FlutterPlugin.FlutterPluginBinding
    private lateinit var rustoreClient: FlutterRustorePushClient

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        flutterBinding = binding

        Log.d(
            "Trying to resolve Application from Context: ${binding.applicationContext.javaClass.name}"
        )
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    }

    override fun onNewIntent(intent: Intent): Boolean {
        Log.d("onNewIntent")
        val message = rustoreClient.getMessageOpenedApp(intent)
        FlutterRustorePushService.onMessageOpenedApp(message)
        return true
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        binding.addOnNewIntentListener(this)

        rustoreClient = FlutterRustorePushClient(binding.activity)
        val callbacks = RuStorePushCallbacks(flutterBinding.binaryMessenger)

        FlutterRustorePushService.client = callbacks
        RuStorePush.setUp(flutterBinding.binaryMessenger, rustoreClient)
    }

    override fun onDetachedFromActivityForConfigChanges() {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    }

    override fun onDetachedFromActivity() {
    }
}
