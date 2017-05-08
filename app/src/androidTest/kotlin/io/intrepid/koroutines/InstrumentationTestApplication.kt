package io.intrepid.koroutines

import android.os.AsyncTask
import io.intrepid.koroutines.api.rest.OkHttpFactory
import io.intrepid.koroutines.api.rest.RestApi
import io.intrepid.koroutines.api.rest.RetrofitClient
import io.intrepid.koroutines.app.KoroutinesApplication
import io.intrepid.koroutines.app.base.PresenterConfiguration
import io.intrepid.koroutines.app.base.logging.CrashReporter
import io.intrepid.koroutines.app.base.settings.SharedPreferencesManager
import io.intrepid.koroutines.app.base.settings.UserSettings
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.mockito.Mockito

class InstrumentationTestApplication : KoroutinesApplication() {
    override fun presenterConfiguration(): PresenterConfiguration {
        return PresenterConfiguration(
                // using AsyncTask executor since Espresso automatically waits for it to clear before proceeding
                Schedulers.from(AsyncTask.SERIAL_EXECUTOR),
                AndroidSchedulers.mainThread(),
                userSettingsOverride ?: SharedPreferencesManager.getInstance(this),
                restApiOverride ?: RetrofitClient.getInstance(OkHttpFactory.getInstance(null, null).client).restApi,
                Mockito.mock(CrashReporter::class.java)
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
