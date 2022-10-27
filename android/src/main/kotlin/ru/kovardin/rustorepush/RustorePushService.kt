package ru.kovardin.rustorepush

import android.util.Log
import ru.rustore.sdk.pushclient.messaging.exception.RuStorePushClientException
import ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService

class RustorePushService: RuStoreMessagingService() {
    companion object {
        var client: RustorePushClient? = null
    }

    override fun onNewToken(token: String) {
        Log.e("onNewToken", token)

        client?.onNewToken(token)
    }

    override fun onError(errors: List<RuStorePushClientException>) {
        Log.e("onError", errors.toString())

        client?.onErrors(errors.toString())
    }
}