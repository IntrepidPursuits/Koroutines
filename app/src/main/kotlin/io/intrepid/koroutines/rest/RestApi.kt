package io.intrepid.koroutines.rest

import io.intrepid.koroutines.models.User
import io.reactivex.Single
import retrofit2.http.GET

interface RestApi {
    @GET("/?format=json")
    fun getMyIp(): Single<User>
}
