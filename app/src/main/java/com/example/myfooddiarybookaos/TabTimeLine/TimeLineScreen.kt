package com.example.myfooddiarybookaos.TabTimeLine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myfooddiarybookaos.Layout.NotDataView
import com.example.myfooddiarybookaos.Layout.TopCalendarLayout
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import java.util.*

@Composable
fun TimeLineScreen() {
    Column {
        // 캘린더 초기화
        val calendarDate = Calendar.getInstance()
        TopCalendarLayout(calendarDate = calendarDate)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            // 데이터 없음 표시
            Box(contentAlignment = Alignment.Center) {
                NotDataView()
            }
        }
    }
}