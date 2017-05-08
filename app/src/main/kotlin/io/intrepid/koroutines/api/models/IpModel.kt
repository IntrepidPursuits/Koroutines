package io.intrepid.koroutines.api.models

import io.mironov.smuggler.AutoParcelable

data class IpModel(val ip: String = "") : AutoParcelable
