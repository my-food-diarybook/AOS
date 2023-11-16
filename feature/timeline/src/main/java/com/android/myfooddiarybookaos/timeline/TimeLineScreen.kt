package com.android.myfooddiarybookaos.TabTimeLine

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.Layout.TopCalendarLayout
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.timeline.ui.LoadTimeLineScreen
import com.android.myfooddiarybookaos.timeline.viewModel.TimeLineViewModel

@Composable
fun TimeLineScreen(
    todayViewModel: TodayViewModel = hiltViewModel(),
    timeLineViewModel: TimeLineViewModel = hiltViewModel()
) {

    val timeLineData = timeLineViewModel.timeLine.observeAsState().value

    LaunchedEffect(Unit){
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