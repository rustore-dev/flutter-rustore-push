package ru.rustore.flutter_rustore_push

import android.app.Application
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.rustore.flutter_rustore_billing.utils.Log
import ru.rustore.flutter_rustore_push.pigeons.Rustore
import ru.rustore.sdk.core.tasks.OnCompleteListener
import ru.rustore.sdk.pushclient.RuStorePushClient
import ru.rustore.sdk.pushclient.common.logger.DefaultLogger

class FlutterRustorePushClient(private val app: Application) : Rustore.Client {
    var initializeResult: Rustore.Result<String>? = null
    var onNewTokenResult: Rustore.Result<String>? = null
    var onMessageReceivedResult: Rustore.Result<Rustore.Message>? = null
    var onMessageDeleteResult: Rustore.Result<Void>? = null
    var onErrorResult: Rustore.Result<String>? = null


    @OptIn(DelicateCoroutinesApi::class)
    override fun initialize(project: String, result: Rustore.Result<String>?) {
        FlutterRustorePushService.client = this
        RuStorePushClient.init(
            application = app,
            projectId = project,
            logger = DefaultLogger()
        )

        initializeResult = result

        GlobalScope.launch {
            Log.i("initialize")

            RuStorePushClient.getToken().addOnCompleteListener(object : OnCompleteListener<String> {
                override fun onFailure(throwable: Throwable) {
                    initializeResult?.error(throwable)
                }

                override fun onSuccess(token: String) {
                    initializeResult?.success(token)
                }
            })
        }
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

        onMessageDeleteResult = result
    }

    override fun onError(result: Rustore.Result<String>) {
        Log.d("onError")

        onErrorResult = result
    }
}
