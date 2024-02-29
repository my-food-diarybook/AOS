package com.android.myfooddiarybookaos.model.search

import com.google.gson.annotations.SerializedName

class SearchDiary(
    @SerializedName("diaryId") val diaryId: Int,
    @SerializedName("bytes") val bytes: String
) : java.io.Serializable
