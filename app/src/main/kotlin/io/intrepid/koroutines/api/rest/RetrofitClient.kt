package io.intrepid.koroutines.api.rest

import android.support.annotation.VisibleForTesting
import com.serjltt.moshi.adapters.WrappedJsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import io.intrepid.koroutines.BuildConfig
import io.intrepid.koroutines.api.moshi.UUIDMoshiAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.*

class RetrofitClient private constructor(httpClient: OkHttpClient?) {

    companion object {
        private var instance: RetrofitClient? = null
        fun getInstance(httpClient: OkHttpClient?): RetrofitClient {
            return instance ?: RetrofitClient(httpClient).apply { instance = this }
        }
    }

    // TODO: change this to a real endpoint
    private val BASE_URL = "https://api.ipify.org/"
    val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory)
            .add(WrappedJsonAdapter.FACTORY)
            .add(UUIDMoshiAdapter())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()

    val restApi: RestApi by lazy { createRestApi(BASE_URL, httpClient) }

    @VisibleForTesting
    internal fun getTestApi(baseUrl: String, httpClient: OkHttpClient?): RestApi {
        return createRestApi(baseUrl, httpClient)
    }

    private fun createRestApi(baseUrl: String, httpClient: OkHttpClient?): RestApi {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.LOG_CONSOLE) {
            builder.addInterceptor(HttpLoggingInterceptor({ Timber.v(it) }).setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RestApi::class.java)
    }
}
