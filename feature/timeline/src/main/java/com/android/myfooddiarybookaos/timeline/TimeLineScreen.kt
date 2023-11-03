package com.android.myfooddiarybookaos.TabTimeLine

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.Layout.TopCalendarLayout

@Composable
fun TimeLineScreen() {
    Column {
        // 캘린더 초기화
        TopCalendarLayout()
        NotDataView()
    }
}