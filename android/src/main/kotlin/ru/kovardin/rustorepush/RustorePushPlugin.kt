package ru.kovardin.rustorepush

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
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
class RustorePushPlugin: FlutterPlugin {
  private lateinit var channel : MethodChannel

  private lateinit var context: Context
  private lateinit var application: Application

  override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    context = binding.applicationContext
    while (context != null) {
      Log.w("RustorePushPlugin", "Trying to resolve Application from Context: ${context.javaClass.name}")
      application = context as Application
      if (application != null) {
        Log.i("", "Resolved Application from Context")
        break
      } else {
        context = context.applicationContext
      }
    }

    if (application == null) {
      Log.e("RustorePushPlugin", "Fail to resolve Application from Context")
    }

//    Log.e("RustorePushPlugin", activity.localClassName)

    val rustore = RustorePushClient(application)
    Rustore.PushClient.setup(binding.binaryMessenger, rustore)
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
