package com.android.myfooddiarybookaos.TabTimeLine

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.Layout.TopCalendarLayout
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModel

@Composable
fun TimeLineScreen(
    todayViewModel: TodayViewModel = viewModel()
) {
    Column {
        // 캘린더 초기화
        TopCalendarLayout(todayViewModel)
        NotDataView()
    }
}