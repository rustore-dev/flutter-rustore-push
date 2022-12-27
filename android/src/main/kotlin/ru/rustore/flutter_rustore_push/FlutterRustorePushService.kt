package ru.rustore.flutter_rustore_push

import android.app.Application
import ru.rustore.flutter_rustore_billing.utils.Log
import ru.rustore.flutter_rustore_push.pigeons.Rustore
import ru.rustore.flutter_rustore_push.utils.Resource
import ru.rustore.sdk.pushclient.RuStorePushClient
import ru.rustore.sdk.pushclient.common.logger.DefaultLogger
import ru.rustore.sdk.pushclient.messaging.exception.RuStorePushClientException
import ru.rustore.sdk.pushclient.messaging.model.RemoteMessage
import ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService

class FlutterRustorePushService : RuStoreMessagingService() {
    companion object {
        var client: FlutterRustorePushClient? = null
    }

    override fun onNewToken(token: String) {
        Log.d("onNewToken: $token")

        client?.onNewTokenResult?.success(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("onMessageReceived: $message")

        val result = Rustore.Message.Builder()
        result.setMessageId(message.messageId)
        result.setData(message.data)
        result.setPriority(message.priority.toLong())
        result.setTtl(message.ttl.toLong())
        result.setCollapseKey(message.collapseKey)
        result.setNotification(
            Rustore.Notification.Builder()
                .setTitle(message.notification?.title)
                .setBody(message.notification?.body)
                .setChannelId(message.notification?.channelId)
                .setClickAction(message.notification?.clickAction)
                .setIcon(message.notification?.icon)
                .setColor(message.notification?.color)
                .setImageUrl(message.notification?.imageUrl.toString())
                .build()
        )

        client?.onMessageReceivedResult?.success(result.build())
    }

    override fun onDeletedMessages() {
        Log.d("onDeletedMessages")

        client?.onDeletedMessagesResult?.success(null)
    }

    override fun onError(errors: List<RuStorePushClientException>) {
        Log.d("onError: $errors")

        client?.onErrorResult?.success(errors.toString())
    }
}
