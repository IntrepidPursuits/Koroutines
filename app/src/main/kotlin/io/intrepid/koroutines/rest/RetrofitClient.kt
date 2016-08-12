package io.intrepid.koroutines.rest

import android.support.annotation.VisibleForTesting
import io.intrepid.koroutines.rest.MoshiParser.moshi
import okhttp3.OkHttpClient
import retrofit.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient(private val okHTTPClient: OkHttpClient) {

    // TODO: change this to a real endpoint
    @Suppress("PrivatePropertyName")
    private val BASE_URL = "https://api.ipify.org/"
    val restApi: RestApi by lazy { createRestApi(okHTTPClient, BASE_URL) }

    @VisibleForTesting
    internal fun getTestApi(baseUrl: String): RestApi {
        return createRestApi(okHTTPClient, baseUrl)
    }

    private fun createRestApi(httpClient: OkHttpClient, baseUrl: String): RestApi {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create()
    }

    private inline fun <reified T : Any> Retrofit.create(): T = create(T::class.java)
}
