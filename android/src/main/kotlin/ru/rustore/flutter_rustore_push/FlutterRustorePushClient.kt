package ru.rustore.flutter_rustore_push

import ru.rustore.flutter_rustore_push.pigeons.RuStorePush
import ru.rustore.flutter_rustore_push.utils.Log
import ru.rustore.sdk.core.feature.model.FeatureAvailabilityResult
import ru.rustore.sdk.pushclient.RuStorePushClient

class FlutterRustorePushClient : RuStorePush {

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
}
