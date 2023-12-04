package com.android.myfooddiarybookaos.model.detail

import com.android.myfooddiarybookaos.model.image.Image
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class DiaryDetail (
    @SerializedName("memo") val memo: String = "",
    @SerializedName("diaryTime") val diaryTime: String = "",
    @SerializedName("date") val date: String ="",
    @SerializedName("tags") val tags: List<Tag> = listOf(),
    @SerializedName("place") val place: String? = null,
    @SerializedName("longitude") val longitude: Double? = null,
    @SerializedName("latitude") val latitude: Double? = null,
    @SerializedName("images") val images: List<Image> = listOf(),
): Serializable