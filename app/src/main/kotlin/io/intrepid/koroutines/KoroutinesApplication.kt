package io.intrepid.koroutines

import android.app.Application
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.leakcanary.LeakCanary
import io.intrepid.koroutines.base.PresenterConfiguration
import io.intrepid.koroutines.rest.OkHttpFactory
import io.intrepid.koroutines.rest.RetrofitClient
import io.intrepid.koroutines.settings.SharedPreferencesManager
import io.intrepid.koroutines.settings.UserSettings
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import okhttp3.OkHttpClient
import timber.log.Timber

open class KoroutinesApplication : Application() {

    private val userSettings: UserSettings by lazy { SharedPreferencesManager.getInstance(this) }
    private val okHTTPClient: OkHttpClient by lazy { OkHttpFactory(userSettings, ChuckInterceptor(this)).okHTTPClient }
    private val restApi by lazy { RetrofitClient(okHTTPClient).restApi }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        LeakCanary.install(this)
    }

    open fun getPresenterConfiguration(): PresenterConfiguration {
        return PresenterConfiguration(CommonPool, UI, userSettings, restApi)
    }
}
