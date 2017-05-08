package io.intrepid.koroutines.api.rest

import com.readystatesoftware.chuck.ChuckInterceptor
import io.intrepid.koroutines.BuildConfig
import io.intrepid.koroutines.app.base.settings.UserSettings
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

class OkHttpFactory private constructor(userSettings: UserSettings?, chuck: ChuckInterceptor?) {
    private val CONNECTION_TIMEOUT = 30
    val client: OkHttpClient = getOkHttpClient(userSettings, chuck)

    companion object {
        private var instance: OkHttpFactory? = null
        fun getInstance(userSettings: UserSettings?, chuck: ChuckInterceptor?): OkHttpFactory {
            return instance ?: OkHttpFactory(userSettings, chuck).apply { instance = this }
        }
    }

    //TODO Remove this suppression along with the other todo message
    @Suppress("UNUSED_PARAMETER")
    fun getOkHttpClient(userSettings: UserSettings?, chuck: ChuckInterceptor?): OkHttpClient {
        val builder = OkHttpClient.Builder()

        // Chuck network monitor
        if (chuck != null) {
            builder.addInterceptor(chuck)
        }

        //TODO Authorization Interceptor Example
        //        // Authorization Interceptor
        //        builder.addInterceptor(chain -> {
        //            Request request = chain.request();
        //            String token = userSettings.getAuthToken();
        //            Request.Builder signedRequest = request.newBuilder();
        //            if (token != null) {
        //                signedRequest.addHeader("Authorization", "Bearer " + token);
        //            }
        //            signedRequest.addHeader("Host", "irate-server.herokuapp.com");
        //            return chain.proceed(signedRequest.build());
        //        });

        // Verbose debugging
        if (BuildConfig.LOG_CONSOLE) {
            builder.addInterceptor(HttpLoggingInterceptor { message -> Timber.v(message) }.setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return builder
                .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
    }
}

