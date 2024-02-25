package com.android.myfooddiarybookaos.home.function

import java.time.LocalDate

fun getLocalDateDayOfWeek(
    date: LocalDate
): String {
    return when (date.dayOfWeek.value) {
        1 -> "월"
        2 -> "화"
        3 -> "수"
        4 -> "목"
        5 -> "금"
        6 -> "토"
        else -> "일"
    }
}
