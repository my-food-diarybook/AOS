package com.android.myfooddiarybookaos.timeline

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.data.component.TopCalendarLayout
import com.android.myfooddiarybookaos.timeline.ui.LoadTimeLineScreen
import com.android.myfooddiarybookaos.timeline.viewModel.TimeLineViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel

@Composable
fun TimeLineScreen(
    todayViewModel: TodayViewModel = hiltViewModel(),
    timeLineViewModel: TimeLineViewModel = hiltViewModel()
) {

    val timeLineData = timeLineViewModel.timeLine.value

    LaunchedEffect(Unit){
        timeLineViewModel.setTimeLineData(todayViewModel.getTodayDate())
    }

    // 여기 수정 필요 -> 현재 데이터로 로드
    todayViewModel.getDataChange().observeAsState().value?.let {
        timeLineViewModel.setTimeLineData(todayViewModel.getTodayDate())
    }

    Column {
        // 캘린더 초기화
        TopCalendarLayout(todayViewModel)

        if (timeLineData == null || timeLineData.isEmpty()) {
            NotDataView()
        } else {
            LoadTimeLineScreen(timeLineData)
        }
    }
}