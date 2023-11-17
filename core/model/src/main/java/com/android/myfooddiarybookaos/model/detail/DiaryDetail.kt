package com.android.myfooddiarybookaos.model.detail

import com.android.myfooddiarybookaos.model.image.Image
import com.google.gson.annotations.SerializedName


class DiaryDetail (
    @SerializedName("memo") val memo: String,
    @SerializedName("diaryTime") val diaryTime: String,
    @SerializedName("date") val date: String,
    @SerializedName("tags") val tags: List<Tag>,
    @SerializedName("place") val place: String?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("images") val images: List<Image>,
): java.io.Serializable