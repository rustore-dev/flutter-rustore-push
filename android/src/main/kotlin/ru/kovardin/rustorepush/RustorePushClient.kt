package ru.kovardin.rustorepush

import android.app.Application
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.kovardin.rustorepush.pigeons.RustorePush
import ru.rustore.sdk.core.tasks.OnCompleteListener
import ru.rustore.sdk.pushclient.RuStorePushClient
import ru.rustore.sdk.pushclient.common.logger.DefaultLogger

class RustorePushClient(private val app: Application) : RustorePush.PushClient {
    var initializeResult: RustorePush.Result<String>? = null
    var onNewTokenResult: RustorePush.Result<String>? = null
    var onMessageReceivedResult: RustorePush.Result<RustorePush.Message>? = null
    var onMessageDeleteResult: RustorePush.Result<Void>? = null
    var onErrorResult: RustorePush.Result<String>? = null


    @OptIn(DelicateCoroutinesApi::class)
    override fun initialize(project: String, result: RustorePush.Result<String>?) {
        RustorePushService.client = this
        RuStorePushClient.init(
            application = app,
            projectId = project,
            logger = DefaultLogger()
        )

        initializeResult = result

        GlobalScope.launch {
            Log.i("RustorePushClient", "initialize")

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

    override fun onNewToken(result: RustorePush.Result<String>) {
        Log.d("RustorePushClient", "onNewToken")

        onNewTokenResult = result
    }

    override fun onMessageReceived(result: RustorePush.Result<RustorePush.Message>) {
        Log.d("RustorePushClient", "onMessageReceived")

        onMessageReceivedResult = result
    }

    override fun onDeletedMessages(result: RustorePush.Result<Void>) {
        Log.d("RustorePushClient", "onDeletedMessages")

        onMessageDeleteResult = result
    }

    override fun onError(result: RustorePush.Result<String>) {
        Log.d("RustorePushClient", "onError")

        onErrorResult = result
    }
}
