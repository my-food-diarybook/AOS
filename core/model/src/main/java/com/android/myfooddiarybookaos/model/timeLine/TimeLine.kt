package com.android.myfooddiarybookaos.model.timeLine

import com.google.gson.annotations.SerializedName

class TimeLine(
    @SerializedName("date") val date: String,
    @SerializedName("diaryList") val diaryList: List<TimeLineDiary>
):java.io.Serializable