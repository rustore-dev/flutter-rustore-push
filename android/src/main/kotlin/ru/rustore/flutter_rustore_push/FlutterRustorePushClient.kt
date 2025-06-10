package ru.rustore.flutter_rustore_push

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import ru.rustore.flutter_rustore_push.pigeons.Message
import ru.rustore.flutter_rustore_push.pigeons.Notification
import ru.rustore.sdk.pushclient.messaging.model.RemoteMessage
import ru.rustore.flutter_rustore_push.pigeons.RuStorePush
import ru.rustore.flutter_rustore_push.utils.Log
import ru.rustore.sdk.core.feature.model.FeatureAvailabilityResult
import ru.rustore.sdk.pushclient.RuStorePushClient

class FlutterRustorePushClient(private val activity: Activity?) : RuStorePush {

    override fun available(callback: (Result<Boolean>) -> Unit) {
        RuStorePushClient.checkPushAvailability()
            .addOnSuccessListener { result ->
                when (result) {
                    is FeatureAvailabilityResult.Available -> {
                        callback(Result.success(true))
                    }

                    is FeatureAvailabilityResult.Unavailable -> {
                        callback(Result.success(false))
                    }
                }
            }
            .addOnFailureListener { throwable ->
                callback(Result.failure(throwable))
            }
    }

    override fun getToken(callback: (Result<String>) -> Unit) {
        Log.d("isInitialized ${RuStorePushClient.isInitialized}")
        RuStorePushClient.getToken()
            .addOnSuccessListener { token ->
                callback(Result.success(token))
            }
            .addOnFailureListener { throwable ->
                callback(Result.failure(throwable))
            }
    }

    override fun deleteToken(callback: (Result<Unit>) -> Unit) {
        RuStorePushClient.deleteToken()
            .addOnSuccessListener { response ->
                callback(Result.success(response))
            }.addOnFailureListener { throwable ->
                callback(Result.failure(throwable))
            }
    }

    override fun subscribeToTopic(topicName: String, callback: (Result<Unit>) -> Unit) {
        RuStorePushClient.subscribeToTopic(topicName)
            .addOnSuccessListener { response ->
                callback(Result.success(response))
            }.addOnFailureListener { throwable ->
                callback(Result.failure(throwable))
            }
    }

    override fun unsubscribeFromTopic(topicName: String, callback: (Result<Unit>) -> Unit) {
        RuStorePushClient.unsubscribeFromTopic(topicName)
            .addOnSuccessListener { response ->
                callback(Result.success(response))
            }.addOnFailureListener { throwable ->
                callback(Result.failure(throwable))
            }
    }

    override fun getInitialMessage(callback: (Result<Message?>) -> Unit) {
        Log.d("getInitialMessage")
        val messageId = getMessageIdFromIntent(activity?.intent)
        Log.d("messageId: $messageId")
        val message = FlutterRustorePushService.messages[messageId]
        if (message != null)
            callback(Result.success(remoteMessageToMessage(message)))
        else
            callback(Result.success(null))
    }

    fun getMessageOpenedApp(intent: Intent) : Message? {
        val messageId = getMessageIdFromIntent(intent)
        val remoteMsg = FlutterRustorePushService.messages[messageId]
        if (remoteMsg != null) {
            val message = remoteMessageToMessage(remoteMsg)
            Log.d("Push message: $message")
            return message
        } else {
            Log.d("Push message is null")
            return null
        }
    }

    private fun remoteMessageToMessage(message: RemoteMessage): Message {

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

        return msg
    }

    private fun getMessageIdFromIntent(intent: Intent?) : String? {
        Log.d("extras: ${intent?.extras.toString()}")
        return intent?.extras?.getString("vkpns.analytics_payload.message_id")
    }
}
