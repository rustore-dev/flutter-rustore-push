package ru.kovardin.rustorepush

import android.app.Application
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.kovardin.rustorepush.pigeons.Rustore
import ru.rustore.sdk.pushclient.RuStorePushClient
import ru.rustore.sdk.pushclient.common.logger.DefaultLogger

class RustorePushClient(private val app: Application) : Rustore.PushClient {

    override fun initialize() {

        Log.d("RustorePushClient", app.packageName)

        RuStorePushClient.init(
            application = app,
            projectId = "OClMKCDhoBtj3kZ5YAkjmF1BBytBtWEXL",
            logger = DefaultLogger()
        )


        GlobalScope.launch {

            val token = RuStorePushClient.getToken()

            Log.d("RustorePushClient", token.toString())
        }

    }
}
