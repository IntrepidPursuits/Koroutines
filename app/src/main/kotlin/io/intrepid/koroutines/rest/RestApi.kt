package io.intrepid.koroutines.rest

import io.intrepid.koroutines.models.IpModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RestApi {
    @GET("/?format=json")
    fun getMyIp(): Observable<IpModel>
}
