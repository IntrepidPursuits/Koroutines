package io.intrepid.koroutines.rest

import com.readystatesoftware.chuck.ChuckInterceptor
import io.intrepid.koroutines.settings.UserSettings
import io.intrepid.koroutines.utils.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpFactory(userSettings: UserSettings, chuck: ChuckInterceptor) {
    private val connectionTimeout = 30
    val okHTTPClient by lazy { getOkHttpClient(userSettings, chuck) }

    private fun getOkHttpClient(@Suppress("UNUSED_PARAMETER") userSettings: UserSettings, chuck: ChuckInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(chuck)
                .addInterceptor(HttpLoggingInterceptor { LogUtils.logVerboseMessage(this, it) }
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(connectionTimeout.toLong(), TimeUnit.SECONDS)
                .build()
    }
}