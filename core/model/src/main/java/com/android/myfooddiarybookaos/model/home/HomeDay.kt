package com.android.myfooddiarybookaos.model.home

import com.android.myfooddiarybookaos.model.image.Image
import com.google.gson.annotations.SerializedName

class HomeDay(
    @SerializedName("id") val id: Int,
    @SerializedName("memo") val memo: String,
    @SerializedName("diaryTime") val diaryTime: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("place") val place: String,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("image") val image: Image
)
