package com.android.myfooddiarybookaos.home.function

import java.time.LocalDate
import java.util.Calendar

fun getLocalDateDayOfWeek(
    date : LocalDate
): String {
    return when (date.dayOfWeek.value) {
        1 -> "일"
        2 -> "월"
        3 -> "화"
        4 -> "수"
        5 -> "목"
        6 -> "금"
        else -> "토"
    }
}