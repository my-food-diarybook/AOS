package com.android.myfooddiarybookaos.timeline

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.data.component.TopCalendarLayout
import com.android.myfooddiarybookaos.timeline.viewModel.TimeLineViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.timeline.item.TimeLineItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimeLineScreen(
    appState: ApplicationState,
    diaryState : DiaryState,
    todayViewModel: TodayViewModel = hiltViewModel(),
    timeLineViewModel: TimeLineViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit){
        timeLineViewModel.initState(appState,diaryState)
    }

    val viewUpdate = rememberSaveable{ mutableStateOf(true) }
    val timeLineData = timeLineViewModel.timeLine.collectAsLazyPagingItems()

    if (viewUpdate.value){
        rememberCoroutineScope().launch {
            timeLineViewModel.setTimeLineData(todayViewModel.getCurrentTimeLineKey())
            delay(100)
            viewUpdate.value = false
        }
    }

    Column {
        // 캘린더 초기화
        TopCalendarLayout(
            resetData = {
                viewUpdate.value = true
            },
            isMainView = false
        )

        if (!viewUpdate.value) {
            if (timeLineData.itemCount==0) {
                NotDataView()
            } else {
                val screenWidth = LocalConfiguration.current.screenWidthDp.dp

                LazyColumn(
                    state = rememberLazyListState(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    items(timeLineData.itemCount) { index ->
                        timeLineData[index]?.let { timeLine ->
                            TimeLineItem(timeLine = timeLine, screenWidth = screenWidth )
                        }
                    }
                    item(1){
                        Box(Modifier.height(100.dp))
                    }
                }
            }
        }
    }
}