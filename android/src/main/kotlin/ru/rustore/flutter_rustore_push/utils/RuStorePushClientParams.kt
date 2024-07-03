package ru.rustore.flutter_rustore_push.utils

import android.content.Context
import com.vk.push.common.clientid.ClientId
import com.vk.push.common.clientid.ClientIdCallback
import com.vk.push.common.clientid.ClientIdType
import ru.rustore.sdk.pushclient.provider.AbstractRuStorePushClientParams

//class RuStorePushClientParams(context: Context) : AbstractRuStorePushClientParams(context) {
////    override fun getLogger(): Logger = DefaultLogger("your_tag")
//
//    override fun getTestModeEnabled(): Boolean = true
//
//    override fun getClientIdCallback(): ClientIdCallback =
//        ClientIdCallback { ClientId("your_gaid_or_oaid", ClientIdType.GAID) }
//}