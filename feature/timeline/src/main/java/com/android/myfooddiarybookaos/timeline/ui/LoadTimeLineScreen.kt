package com.android.myfooddiarybookaos.timeline.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.model.timeLine.TimeLine
import com.android.myfooddiarybookaos.timeline.item.TimeLineItem
import com.android.myfooddiarybookaos.timeline.viewModel.TimeLineViewModel

@Composable
fun LoadTimeLineScreen(
    timeLinData: List<TimeLine>
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier.fillMaxSize()
    ) {
        items(timeLinData) { timeLine ->
            TimeLineItem(timeLine = timeLine, screenWidth = screenWidth )
        }
    }
}