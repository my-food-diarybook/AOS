package com.android.myfooddiarybookaos.data.function


enum class DiaryTime(private val nameCode: String) {
    BREAKFAS("아침"),
    BRUNCH("아점"),
    LUNCH("점심"),
    LINNER("점저"),
    SNACK("간식"),
    DINNER("저녁"),
    LATESNACK("야식"),
    NO("기타");

    companion object {
        fun getDiaryTimeData(code: String): String {
            return when (code) {
                "BREAKFAST" -> BREAKFAS.nameCode
                "BRUNCH" -> BRUNCH.nameCode
                "LUNCH" -> LUNCH.nameCode
                "SNACK" -> SNACK.nameCode
                "LINNER" -> LINNER.nameCode
                "DINNER" -> DINNER.nameCode
                "LATESNACK" -> LATESNACK.nameCode
                else -> NO.nameCode
            }
        }
    }
}
