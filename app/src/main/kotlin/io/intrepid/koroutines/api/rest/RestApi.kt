package io.intrepid.koroutines.api.rest

import io.intrepid.koroutines.api.models.IpModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface RestApi {
    @GET("/?format=json")
    fun getMyIp(): Single<IpModel>
}
