package io.intrepid.koroutines.api.rest

import io.intrepid.koroutines.rules.MockServerRule
import okhttp3.OkHttpClient

object TestRestClient {
    fun getRestApi(mockServer: MockServerRule): RestApi {
        return RetrofitClient.getInstance(null).getTestApi(mockServer.serverUrl, null)
    }
}
