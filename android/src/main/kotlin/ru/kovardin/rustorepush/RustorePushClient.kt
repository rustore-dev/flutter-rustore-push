package ru.kovardin.rustorepush

import android.app.Application
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.kovardin.rustorepush.pigeons.Rustore
import ru.rustore.sdk.pushclient.RuStorePushClient
import ru.rustore.sdk.pushclient.common.logger.DefaultLogger

class RustorePushClient(private val app: Application) : Rustore.PushClient {
    var token: String = ""
    var errors: String = ""
    var callback: Rustore.Result<String>? = null

    override fun initialize(project: String, result: Rustore.Result<String>?) {
        RustorePushService.client = this
        RuStorePushClient.init(
            application = app,
            projectId = project,
            logger = DefaultLogger()
        )

        GlobalScope.launch {
            Log.i("RustorePushClient", RuStorePushClient.getToken().toString())

            val token = RuStorePushClient.getToken()
            if (token != null) {
                result?.success(token)
            } else {
               callback = result
            }
        }
    }

    fun onNewToken(token: String) {
        this.token = token

        callback?.success(token)
    }

    fun onErrors(errors: String) {
        this.errors = errors
    }
}
