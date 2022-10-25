package ru.kovardin.rustorepush

import android.app.Application
import android.content.Context
import ru.kovardin.rustorepush.pigeons.Rustore
import ru.rustore.sdk.pushclient.RuStorePushClient
import ru.rustore.sdk.pushclient.common.logger.DefaultLogger

class RustorePushClient(private val context: Context): Rustore.PushClient {

    override fun initialize() {
        RuStorePushClient.init(
            application = context as Application,
            projectId = "OClMKCDhoBtj3kZ5YAkjmF1BBytBtWEXL",
            logger = DefaultLogger()
        )
    }
}
