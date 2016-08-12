package io.intrepid.koroutines.rest

import io.intrepid.koroutines.rules.MockServerRule
import okhttp3.OkHttpClient

object TestRestClient {
    fun getRestApi(mockServer: MockServerRule): RestApi {
        return RetrofitClient(OkHttpClient()).getTestApi(mockServer.serverUrl)
    }
}
