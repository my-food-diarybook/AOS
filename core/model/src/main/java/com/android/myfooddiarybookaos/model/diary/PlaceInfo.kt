package com.android.myfooddiarybookaos.model.diary

import com.google.gson.annotations.SerializedName

data class PlaceInfo(
    @SerializedName("place") val place: String?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("latitude") val latitude: Double?
) : java.io.Serializable
