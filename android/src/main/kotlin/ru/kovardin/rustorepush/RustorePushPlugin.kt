package ru.kovardin.rustorepush

import android.app.Activity
import android.content.Context
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import ru.kovardin.rustorepush.pigeons.Rustore

/** RustorePushPlugin */
class RustorePushPlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
  private lateinit var channel : MethodChannel

  private lateinit var context: Context
  private lateinit var activity: Activity

  override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    context = binding.applicationContext

    val rustore = RustorePushClient(context)
    Rustore.PushClient.setup(binding.binaryMessenger, rustore)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {

  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    TODO("Not yet implemented")
  }

  override fun onDetachedFromActivityForConfigChanges() {
    TODO("Not yet implemented")
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    TODO("Not yet implemented")
  }

  override fun onDetachedFromActivity() {
    TODO("Not yet implemented")
  }
}
