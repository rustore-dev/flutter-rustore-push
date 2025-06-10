package ru.rustore.flutter_rustore_push

import android.os.Handler
import android.os.Looper
import ru.rustore.flutter_rustore_push.pigeons.Message
import ru.rustore.flutter_rustore_push.pigeons.Notification
import ru.rustore.flutter_rustore_push.pigeons.RuStorePushCallbacks
import ru.rustore.flutter_rustore_push.utils.Log
import ru.rustore.sdk.pushclient.messaging.exception.RuStorePushClientException
import ru.rustore.sdk.pushclient.messaging.model.RemoteMessage
import ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService


class FlutterRustorePushService : RuStoreMessagingService() {
    companion object {
        val messages = mutableMapOf<String, RemoteMessage>()
        var client: RuStorePushCallbacks? = null
        fun onMessageOpenedApp(message: Message?) {
            val uiThreadHandler: Handler = Handler(Looper.getMainLooper())
            Log.d("onMessageOpenedApp: $message")
            if(message != null) {
                uiThreadHandler.post {
                    client?.messageOpenedApp(message) { }
                }
            }
        }
    }

    private val uiThreadHandler: Handler = Handler(Looper.getMainLooper())

    override fun onNewToken(token: String) {
        Log.d("onNewToken: $token")

        uiThreadHandler.post {
            client?.newToken(token) { }
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("onMessageReceived: $message")
        message.messageId?.let {
            messages[it] = message
        }
        var notification: Notification? = null

        if (message.notification != null) {
            notification = Notification(
                title = message.notification?.title.orEmpty(),
                body = message.notification?.body.orEmpty(),
                channelId = message.notification?.channelId.orEmpty(),
                clickAction = message.notification?.clickAction.orEmpty(),
                icon = message.notification?.icon.orEmpty(),
                color = message.notification?.color.orEmpty(),
                imageUrl = message.notification?.imageUrl.toString(),
            )
        }

        val msg = Message(
            messageId = message.messageId,
            data = message.data as Map<String?, String?>,
            priority = message.priority.toLong(),
            ttl = message.ttl.toLong(),
            collapseKey = message.collapseKey,
            notification = notification
        )

        uiThreadHandler.post {
            client?.messageReceived(msg) { }
        }
    }

    override fun onDeletedMessages() {
        Log.d("onDeletedMessages")

        uiThreadHandler.post {
            client?.deletedMessages { }
        }

    }

    override fun onError(errors: List<RuStorePushClientException>) {
        Log.d("onError: $errors")

        uiThreadHandler.post {
            client?.error(errors.toString()) { }
        }
    }
}
