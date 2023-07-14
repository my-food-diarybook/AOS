package com.example.myfooddiarybookaos.TabTimeLine

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.myfooddiarybookaos.Layout.NotDataView
import com.example.myfooddiarybookaos.Layout.TopCalendarLayout
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import java.util.*

@Composable
fun TimeLineScreen(){
    Column {
        // 캘린더 초기화
        val calendarDate = Calendar.getInstance()
        TopCalendarLayout(
            monthString =
            "${calendarDate.get(Calendar.YEAR)}" +
                    ".${calendarDate.get(Calendar.MONTH)
                        .plus(1)}"
        )
        NotDataView()
    }
}