package com.android.myfooddiarybookaos.model.detail

import com.google.gson.annotations.SerializedName

class FixDiary(
    @SerializedName("memo") val memo: String,
    @SerializedName("diaryTime") val diaryTime: String,
    @SerializedName("tags") val tags: List<Tag>,
    @SerializedName("place") val place: String?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("latitude") val latitude: Double?,
)
