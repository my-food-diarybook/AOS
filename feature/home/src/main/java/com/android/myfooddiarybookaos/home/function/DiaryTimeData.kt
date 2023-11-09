package com.android.myfooddiarybookaos.home.function

fun diaryTimeData(code: String): String {
    return when (code) {
        "BREAKFAST" -> "아침"
        "BRUNCH" -> "아점"
        "LUNCH" -> "점심"
        "SNACK" -> "간식"
        "LINNER" -> "점저"
        "DINNER" -> "저녁"
        "LATESNACK" -> "야식"
        else -> "기타"
    }
}

