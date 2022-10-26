package ru.kovardin.rustorepush

import android.util.Log
import ru.rustore.sdk.pushclient.messaging.exception.RuStorePushClientException
import ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService

class RustorePushService: RuStoreMessagingService() {
    override fun onNewToken(token: String) {
        println(token)
        Log.e("onNewToken", token)
    }

    override fun onError(errors: List<RuStorePushClientException>) {
//        println(token)
        Log.e("onError", errors.toString())
    }
}