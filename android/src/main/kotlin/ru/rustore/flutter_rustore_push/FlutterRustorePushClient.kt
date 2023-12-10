package ru.rustore.flutter_rustore_push

import android.app.Application
import com.vk.push.common.clientid.ClientId
import com.vk.push.common.clientid.ClientIdCallback
import com.vk.push.common.clientid.ClientIdType
import ru.rustore.flutter_rustore_push.utils.Log
import ru.rustore.flutter_rustore_push.utils.Resource
import ru.rustore.flutter_rustore_push.pigeons.RuStorePush
import ru.rustore.flutter_rustore_push.pigeons.ClientId as RuStoreClientId
import ru.rustore.sdk.core.config.SdkType
import ru.rustore.sdk.core.feature.model.FeatureAvailabilityResult
import ru.rustore.sdk.core.tasks.OnCompleteListener
import ru.rustore.sdk.pushclient.RuStorePushClient
import ru.rustore.sdk.pushclient.common.logger.DefaultLogger

const val CLIENT_ID_TYPE_GAID = "gaid"
const val CLIENT_ID_TYPE_OAID = "oaid"

class FlutterRustorePushClient(private val app: Application) : RuStorePush {
    companion object{
        fun initialization(app: Application){
            val project =
                Resource.getResourceFromContext(app, "flutter_rustore_push_project")
            Log.d("project: ${project}")
            RuStorePushClient.init(
                application = app,
                projectId = project ?: "",
                logger = DefaultLogger(),
                internalConfig = mapOf("type" to SdkType.FLUTTER)
            )
        }
    }

    override fun initialization(project: String, client: RuStoreClientId?, callback: (Result<Unit>) -> Unit) {
        Log.d("project: ${project}")

        val clientIdType: ClientIdType = when(client?.type) {
            CLIENT_ID_TYPE_GAID -> {
                ClientIdType.GAID
            }

            CLIENT_ID_TYPE_OAID -> {
                ClientIdType.OAID
            }

            else -> {
                ClientIdType.GAID
            }
        }

        RuStorePushClient.init(
            application = app,
            projectId = project,
            logger = DefaultLogger(),
            internalConfig = mapOf("type" to SdkType.FLUTTER),
            clientIdCallback = object : ClientIdCallback {
                override fun getClientId(): ClientId {
                    return ClientId(
                        clientIdType = clientIdType,
                        clientIdValue = client?.id.orEmpty(),
                    )
                }
            }
        )

        callback(Result.success(Unit))
    }


    override fun available(callback: (Result<Boolean>) -> Unit) {
        RuStorePushClient.checkPushAvailability()
            .addOnCompleteListener(object : OnCompleteListener<FeatureAvailabilityResult> {
                override fun onSuccess(response: FeatureAvailabilityResult) {
                    when (response) {
                        is FeatureAvailabilityResult.Available -> {
                            callback(Result.success(true))
                        }

                        is FeatureAvailabilityResult.Unavailable -> {
                            callback(Result.success(false))
                        }
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    callback(Result.failure(throwable))
                }
            })
    }

    override fun getToken(callback: (Result<String>) -> Unit) {
        Log.d("isInitialized ${RuStorePushClient.isInitialized}")
        RuStorePushClient.getToken().addOnCompleteListener(object : OnCompleteListener<String> {
            override fun onSuccess(token: String) {
                callback(Result.success(token))
            }

            override fun onFailure(throwable: Throwable) {
                callback(Result.failure(throwable))
            }
        })
    }

    override fun deleteToken(callback: (Result<Unit>) -> Unit) {
        RuStorePushClient.deleteToken().addOnCompleteListener(object : OnCompleteListener<Unit> {
            override fun onSuccess(response: Unit) {
                callback(Result.success(Unit))
            }

            override fun onFailure(throwable: Throwable) {
                callback(Result.failure(throwable))
            }
        })
    }
}
