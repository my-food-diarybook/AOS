package com.android.myfooddiarybookaos.timeline

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.data.component.TopCalendarLayout
import com.android.myfooddiarybookaos.timeline.ui.LoadTimeLineScreen
import com.android.myfooddiarybookaos.timeline.viewModel.TimeLineViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimeLineScreen(
    todayViewModel: TodayViewModel = hiltViewModel(),
    timeLineViewModel: TimeLineViewModel = hiltViewModel()
) {

    val viewUpdate = rememberSaveable{ mutableStateOf(true) }
    val timeLineData = timeLineViewModel.timeLine.collectAsState()

    if (viewUpdate.value){
        timeLineViewModel.setTimeLineData(todayViewModel.getCurrentTimeLineKey())
        rememberCoroutineScope().launch {
            delay(500)
            viewUpdate.value = false
        }
    }

    Column {
        // 캘린더 초기화
        TopCalendarLayout(
            resetData = {
                viewUpdate.value = true
            }
        )

        if (!viewUpdate.value) {
            if (timeLineData.value.isEmpty()) {
                NotDataView()
            } else {
                LoadTimeLineScreen(timeLineData)
            }
        }
    }
}