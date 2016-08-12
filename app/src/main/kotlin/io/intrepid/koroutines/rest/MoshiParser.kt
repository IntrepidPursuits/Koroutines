package io.intrepid.koroutines.rest

import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

object MoshiParser {
    val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Wrapped.ADAPTER_FACTORY)
            .build()
}