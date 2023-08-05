package com.android.myfooddiarybookaos.TabTimeLine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.Layout.TopCalendarLayout
import com.android.myfooddiarybookaos.ViewModel.TodayViewModelInterface

@Composable
fun TimeLineScreen(
    todayViewModel: TodayViewModelInterface
) {
    Column {
        // 캘린더 초기화
        TopCalendarLayout(todayViewModel)
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