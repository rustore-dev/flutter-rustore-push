package ru.kovardin.rustorepush

import android.util.Log
import ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService

class RustorePushService: RuStoreMessagingService() {
    override fun onNewToken(token: String) {
        println(token)
        Log.e("onNewToken", token)
    }
}