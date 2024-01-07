package com.android.myfooddiarybookaos.timeline.item

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap
import com.android.myfooddiarybookaos.model.timeLine.TimeLine
import com.android.myfooddiarybookaos.timeline.viewModel.ItemViewModel
import com.android.myfooddiarybookaos.timeline.viewModel.TimeLineViewModel

@Composable
fun TimeLineItem(
    todayViewModel: TodayViewModel = hiltViewModel(),
    timeLineViewModel: TimeLineViewModel = hiltViewModel(),
    timeLine: TimeLine,
    screenWidth: Dp
) {
    val viewModel: ItemViewModel = hiltViewModel()
    val imageSize = remember {
        mutableStateOf(
            getImageDp(timeLine.diaryList.size, screenWidth)
        )
    }

    LaunchedEffect(Unit) {
        Log.d("timeLine.datetimeLine.date",timeLine.date)
        viewModel.setTimeLineData(timeLine.date, timeLine.diaryList.size)
    }

    val pagingItems = viewModel.timeLineDiary.collectAsLazyPagingItems()

    val currentFont = remember {
        mutableStateOf(
            if (todayViewModel.getTodayDate() == timeLine.date)
                FontFamily(Font(R.font.roboto_regular, FontWeight.W700))
            else FontFamily(Font(R.font.roboto_regular, FontWeight.W400))
        )
    }
    val todayDate = remember {
        mutableStateOf(
            if (timeLine.date == todayViewModel.getTodayDate()) todayViewModel.getTopDate(timeLine.date)+" 오늘"
            else todayViewModel.getTopDate(timeLine.date)
        )
    }

    Column {
        Text(
            modifier = Modifier.padding(start = 20.dp, bottom = 2.dp, top = 12.dp),
            text = todayDate.value,
            fontFamily = currentFont.value,
            color = Color.Black,
            fontSize = 14.sp
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(134.dp),
            state = rememberLazyListState()
        ) {
//            items(timeLine.diaryList) { diary ->
//                val imageBitmap = remember { mutableStateOf(byteStringToBitmap(diary.bytes)) }
//                ImageItem(
//                    imageBitmap = imageBitmap,
//                    imageSize = imageSize.value,
//                    onClick = {
//                        timeLineViewModel.goDetailView(diary.diaryId)
//                    }
//                )
//            }
            items(pagingItems.itemCount) { idx ->
                pagingItems[idx]?.let { timeLineDiary ->
                    val imageBitmap =
                        remember { mutableStateOf(byteStringToBitmap(timeLineDiary.bytes)) }
                    ImageItem(
                        imageBitmap = imageBitmap,
                        imageSize = imageSize.value,
                        onClick = {
                            // go data
                            timeLineViewModel.goDetailView(timeLineDiary.diaryId)
                        }
                    )
                }
            }
        }
    }

}

fun getImageDp(
    count: Int,
    screenWidth: Dp
): Dp {
    return if (count > 4) (screenWidth / 360) * 80
    else screenWidth / count
}