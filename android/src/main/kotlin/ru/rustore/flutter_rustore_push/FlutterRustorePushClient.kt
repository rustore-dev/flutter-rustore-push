package ru.rustore.flutter_rustore_push

import android.app.Application
import ru.rustore.flutter_rustore_billing.utils.Log
import ru.rustore.flutter_rustore_push.pigeons.Rustore
import ru.rustore.sdk.core.feature.model.FeatureAvailabilityResult
import ru.rustore.sdk.core.tasks.OnCompleteListener
import ru.rustore.sdk.pushclient.RuStorePushClient

class FlutterRustorePushClient(private val app: Application) : Rustore.Client {
    var onNewTokenResult: Rustore.Result<String>? = null
    var onMessageReceivedResult: Rustore.Result<Rustore.Message>? = null
    var onDeletedMessagesResult: Rustore.Result<Void>? = null
    var onErrorResult: Rustore.Result<String>? = null

    override fun available(result: Rustore.Result<Boolean>?) {
        RuStorePushClient.checkPushAvailability(app)
            .addOnCompleteListener(object : OnCompleteListener<FeatureAvailabilityResult> {
                override fun onSuccess(response: FeatureAvailabilityResult) {
                    when (response) {
                        FeatureAvailabilityResult.Available -> {
                            result?.success(true)
                        }

                        is FeatureAvailabilityResult.Unavailable -> {
                            result?.success(false)
                        }
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    result?.error(throwable)
                }
            })
    }

    override fun onNewToken(result: Rustore.Result<String>) {
        Log.d( "onNewToken")

        onNewTokenResult = result
    }

    override fun onMessageReceived(result: Rustore.Result<Rustore.Message>) {
        Log.d("onMessageReceived")

        onMessageReceivedResult = result
    }

    override fun onDeletedMessages(result: Rustore.Result<Void>) {
        Log.d("onDeletedMessages")

        onDeletedMessagesResult = result
    }

    override fun onError(result: Rustore.Result<String>) {
        Log.d("onError")

        onErrorResult = result
    }

    override fun getToken(result: Rustore.Result<String>?) {
        Log.d("isInitialized ${RuStorePushClient.isInitialized}")
        RuStorePushClient.getToken().addOnCompleteListener(object : OnCompleteListener<String> {
            override fun onSuccess(token: String) {
                result?.success(token)
            }

            override fun onFailure(throwable: Throwable) {
                result?.error(throwable)
            }
        })
    }

    override fun deleteToken(result: Rustore.Result<Void>?) {
        RuStorePushClient.deleteToken().addOnCompleteListener(object : OnCompleteListener<Unit> {
            override fun onSuccess(response: Unit) {
               result?.success(null)
            }

            override fun onFailure(throwable: Throwable) {
                result?.error(throwable)
            }
        })
    }
}
