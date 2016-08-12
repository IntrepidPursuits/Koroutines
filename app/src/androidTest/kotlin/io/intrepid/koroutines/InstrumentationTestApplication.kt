package io.intrepid.koroutines

import com.readystatesoftware.chuck.ChuckInterceptor
import io.intrepid.koroutines.base.PresenterConfiguration
import io.intrepid.koroutines.rest.OkHttpFactory
import io.intrepid.koroutines.rest.RestApi
import io.intrepid.koroutines.rest.RetrofitClient
import io.intrepid.koroutines.settings.SharedPreferencesManager
import io.intrepid.koroutines.settings.UserSettings
import kotlinx.coroutines.experimental.CommonPool
import kotlin.coroutines.experimental.EmptyCoroutineContext

class InstrumentationTestApplication : KoroutinesApplication() {
    override fun getPresenterConfiguration(): PresenterConfiguration {
        val restApi = RetrofitClient(OkHttpFactory(userSettingsOverride!!, ChuckInterceptor(this)).okHTTPClient).restApi
        return PresenterConfiguration(
                CommonPool,
                EmptyCoroutineContext,
                userSettingsOverride ?: SharedPreferencesManager.getInstance(this),
                restApiOverride ?: restApi
        )
    }

    companion object {
        private var restApiOverride: RestApi? = null
        private var userSettingsOverride: UserSettings? = null

        fun overrideRestApi(restApi: RestApi) {
            restApiOverride = restApi
        }

        fun clearRestApiOverride() {
            restApiOverride = null
        }

        fun overrideUserSettings(userSettings: UserSettings) {
            userSettingsOverride = userSettings
        }

        fun clearUserSettingsOverride() {
            userSettingsOverride = null
        }
    }
}
