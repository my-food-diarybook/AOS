package com.android.myfooddiarybookaos.model.home

import com.google.gson.annotations.SerializedName

class DiaryHomeDay(
    @SerializedName("beforeDay") val beforeDay: String? = null,
    @SerializedName("afterDay") val afterDay: String? = null,
    @SerializedName("homeDayList") val homeDayList: List<HomeDay> = listOf()
) : java.io.Serializable
