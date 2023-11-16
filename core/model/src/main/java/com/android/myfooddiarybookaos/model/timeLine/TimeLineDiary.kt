package com.android.myfooddiarybookaos.model.timeLine

import com.google.gson.annotations.SerializedName

class TimeLineDiary (
    @SerializedName("diaryId") val diaryId: Int,
    @SerializedName("bytes") val bytes: String
): java.io.Serializable