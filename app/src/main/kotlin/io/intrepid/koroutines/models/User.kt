package io.intrepid.koroutines.models

import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class User(@Json(name = "name") val name: String) : Parcelable
