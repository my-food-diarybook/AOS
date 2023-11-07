package com.android.myfooddiarybookaos.model.home

import com.google.gson.annotations.SerializedName

class DiaryHomeDay (
    @SerializedName("beforeDay") val beforeDay: String,
    @SerializedName("afterday") val afterday: String,
    @SerializedName("homeDayList") val homeDayList: List<HomeDay>
):java.io.Serializable