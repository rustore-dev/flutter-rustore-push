package ru.kovardin.rustorepush

import android.util.Log
import com.vk.push.common.messaging.RemoteMessage
import ru.rustore.sdk.pushclient.messaging.exception.RuStorePushClientException
import ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService

class RustorePushService: RuStoreMessagingService() {
    companion object {
        var client: RustorePushClient? = null
    }

    override fun onNewToken(token: String) {
        Log.e("RustorePushService", "onNewToken: $token")

        client?.onNewTokenResult?.success(token)
    }

//    override fun onMessageReceived(message: RemoteMessage) {
//    }

    override fun onDeletedMessages() {
        Log.e("RustorePushService","onDeletedMessages")

        client?.onMessageDeleteResult?.success(null)
    }

    override fun onError(errors: List<RuStorePushClientException>) {
        Log.e("RustorePushService", "onError: $errors")

        client?.onErrorResult?.success(errors.toString())
    }
}