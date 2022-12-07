package ru.rustore.flutter_rustore_push

import android.util.Log
import ru.rustore.sdk.pushclient.messaging.exception.RuStorePushClientException
import ru.rustore.sdk.pushclient.messaging.model.RemoteMessage
import ru.rustore.sdk.pushclient.messaging.service.RuStoreMessagingService

class FlutterRustorePushService: RuStoreMessagingService() {
    companion object {
        var client: FlutterRustorePushClient? = null
    }

    override fun onNewToken(token: String) {
        Log.e("RustorePushService", "onNewToken: $token")

        client?.onNewTokenResult?.success(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {

    }

    override fun onDeletedMessages() {
        Log.e("RustorePushService","onDeletedMessages")

        client?.onMessageDeleteResult?.success(null)
    }

    override fun onError(errors: List<RuStorePushClientException>) {
        Log.e("RustorePushService", "onError: $errors")

        client?.onErrorResult?.success(errors.toString())
    }
}
