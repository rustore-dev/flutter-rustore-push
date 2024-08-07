// Autogenerated from Pigeon (v14.0.1), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package ru.rustore.flutter_rustore_push.pigeons

import android.util.Log
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

private fun wrapResult(result: Any?): List<Any?> {
  return listOf(result)
}

private fun wrapError(exception: Throwable): List<Any?> {
  if (exception is FlutterError) {
    return listOf(
      exception.code,
      exception.message,
      exception.details
    )
  } else {
    return listOf(
      exception.javaClass.simpleName,
      exception.toString(),
      "Cause: " + exception.cause + ", Stacktrace: " + Log.getStackTraceString(exception)
    )
  }
}

private fun createConnectionError(channelName: String): FlutterError {
  return FlutterError("channel-error",  "Unable to establish connection on channel: '$channelName'.", "")}

/**
 * Error class for passing custom error details to Flutter via a thrown PlatformException.
 * @property code The error code.
 * @property message The error message.
 * @property details The error details. Must be a datatype supported by the api codec.
 */
class FlutterError (
  val code: String,
  override val message: String? = null,
  val details: Any? = null
) : Throwable()

/** Generated class from Pigeon that represents data sent in messages. */
data class Message (
  val messageId: String? = null,
  val priority: Long,
  val ttl: Long,
  val collapseKey: String? = null,
  val data: Map<String?, String?>,
  val notification: Notification? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): Message {
      val messageId = list[0] as String?
      val priority = list[1].let { if (it is Int) it.toLong() else it as Long }
      val ttl = list[2].let { if (it is Int) it.toLong() else it as Long }
      val collapseKey = list[3] as String?
      val data = list[4] as Map<String?, String?>
      val notification: Notification? = (list[5] as List<Any?>?)?.let {
        Notification.fromList(it)
      }
      return Message(messageId, priority, ttl, collapseKey, data, notification)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      messageId,
      priority,
      ttl,
      collapseKey,
      data,
      notification?.toList(),
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class Notification (
  val title: String? = null,
  val body: String? = null,
  val channelId: String? = null,
  val imageUrl: String? = null,
  val color: String? = null,
  val icon: String? = null,
  val clickAction: String? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): Notification {
      val title = list[0] as String?
      val body = list[1] as String?
      val channelId = list[2] as String?
      val imageUrl = list[3] as String?
      val color = list[4] as String?
      val icon = list[5] as String?
      val clickAction = list[6] as String?
      return Notification(title, body, channelId, imageUrl, color, icon, clickAction)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      title,
      body,
      channelId,
      imageUrl,
      color,
      icon,
      clickAction,
    )
  }
}

/** Generated interface from Pigeon that represents a handler of messages from Flutter. */
interface RuStorePush {
  fun available(callback: (Result<Boolean>) -> Unit)
  fun getToken(callback: (Result<String>) -> Unit)
  fun deleteToken(callback: (Result<Unit>) -> Unit)
  fun subscribeToTopic(topicName: String, callback: (Result<Unit>) -> Unit)
  fun unsubscribeFromTopic(topicName: String, callback: (Result<Unit>) -> Unit)

  companion object {
    /** The codec used by RuStorePush. */
    val codec: MessageCodec<Any?> by lazy {
      StandardMessageCodec()
    }
    /** Sets up an instance of `RuStorePush` to handle messages through the `binaryMessenger`. */
    @Suppress("UNCHECKED_CAST")
    fun setUp(binaryMessenger: BinaryMessenger, api: RuStorePush?) {
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.flutter_rustore_push.RuStorePush.available", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            api.available() { result: Result<Boolean> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.flutter_rustore_push.RuStorePush.getToken", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            api.getToken() { result: Result<String> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.flutter_rustore_push.RuStorePush.deleteToken", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            api.deleteToken() { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.flutter_rustore_push.RuStorePush.subscribeToTopic", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val topicNameArg = args[0] as String
            api.subscribeToTopic(topicNameArg) { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.flutter_rustore_push.RuStorePush.unsubscribeFromTopic", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val topicNameArg = args[0] as String
            api.unsubscribeFromTopic(topicNameArg) { result: Result<Unit> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                reply.reply(wrapResult(null))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
@Suppress("UNCHECKED_CAST")
private object RuStorePushCallbacksCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          Message.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          Notification.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is Message -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is Notification -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/** Generated class from Pigeon that represents Flutter messages that can be called from Kotlin. */
@Suppress("UNCHECKED_CAST")
class RuStorePushCallbacks(private val binaryMessenger: BinaryMessenger) {
  companion object {
    /** The codec used by RuStorePushCallbacks. */
    val codec: MessageCodec<Any?> by lazy {
      RuStorePushCallbacksCodec
    }
  }
  fun newToken(tokenArg: String, callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.flutter_rustore_push.RuStorePushCallbacks.newToken"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(tokenArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
  fun messageReceived(messageArg: Message, callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.flutter_rustore_push.RuStorePushCallbacks.messageReceived"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(messageArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
  fun deletedMessages(callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.flutter_rustore_push.RuStorePushCallbacks.deletedMessages"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(null) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
  fun error(errorArg: String, callback: (Result<Unit>) -> Unit) {
    val channelName = "dev.flutter.pigeon.flutter_rustore_push.RuStorePushCallbacks.error"
    val channel = BasicMessageChannel<Any?>(binaryMessenger, channelName, codec)
    channel.send(listOf(errorArg)) {
      if (it is List<*>) {
        if (it.size > 1) {
          callback(Result.failure(FlutterError(it[0] as String, it[1] as String, it[2] as String?)))
        } else {
          callback(Result.success(Unit))
        }
      } else {
        callback(Result.failure(createConnectionError(channelName)))
      } 
    }
  }
}
