package com.android.myfooddiarybookaos.timeline.item

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap
import com.android.myfooddiarybookaos.model.timeLine.TimeLine
import com.android.myfooddiarybookaos.timeline.viewModel.ItemViewModel
import com.android.myfooddiarybookaos.timeline.viewModel.TimeLineViewModel
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun TimeLineItem(
    todayViewModel: TodayViewModel = hiltViewModel(),
    timeLineViewModel: TimeLineViewModel = hiltViewModel(),
    viewModel: ItemViewModel = hiltViewModel(),
    timeLine: TimeLine,
    screenWidth: Dp
) {

    val imageSize = remember {
        mutableStateOf(
            getImageDp(timeLine.diaryList.size, screenWidth)
        )
    }
    val imagePagingNumber = remember {
        mutableStateOf(
            if (timeLine.diaryList.size < 5) 0 else 1
        )
    }
    val timeLineData =  remember { mutableListOf<TimeLineDiary>() }
    when (imagePagingNumber.value) {
        2 -> {
            LaunchedEffect(Unit) {
                viewModel.setTimeLineData(timeLine.date, 5, diaryList = {
                    timeLineData.clear()
                    timeLineData.addAll(it)
                })
            }
        }
        3 -> {
            LaunchedEffect(Unit) {
                viewModel.setTimeLineData(timeLine.date, 9, diaryList = {
                    timeLineData.clear()
                    timeLineData.addAll(it)
                })
            }
        }

        else -> {
            LaunchedEffect(Unit) {
                timeLineData.clear()
                timeLineData.addAll(timeLine.diaryList)
            }
        }
    }


    val currentFont = remember {
        mutableStateOf(
            if (todayViewModel.getTodayDate() == timeLine.date)
                FontFamily(Font(R.font.roboto_regular, FontWeight.W700))
            else FontFamily(Font(R.font.roboto_regular, FontWeight.W400))
        )
    }
    val todayDate = remember {
        mutableStateOf(
            if (timeLine.date == todayViewModel.getTodayDate()) todayViewModel.getTopDate(timeLine.date) + " 오늘"
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
            if (imagePagingNumber.value == 2) {
                items(1){
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(imageSize.value.div(2))
                            .clickable {
                                imagePagingNumber.value = if (timeLine.diaryList.size < 5) 0 else 1
                            },
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.time_line_prev),
                            contentDescription = null
                        )
                    }
                }
            } else if (imagePagingNumber.value == 3) {
                items(1) {
                    Box(
                        modifier = Modifier
                            .width(imageSize.value)
                            .fillMaxHeight()
                            .clickable {
                                imagePagingNumber.value = 2
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.time_line_prev),
                            contentDescription = null
                        )
                    }
                }
            }

            items(timeLineData.size) { idx ->
                if (idx < 4) {
                    val diary = timeLineData[idx]
                    val imageBitmap = remember { mutableStateOf(byteStringToBitmap(diary.bytes)) }
                    ImageItem(
                        imageBitmap = imageBitmap,
                        imageSize = imageSize.value,
                        onClick = {
                            timeLineViewModel.goDetailView(diary.diaryId)
                        }
                    )
                }
            }
            if (imagePagingNumber.value == 1) {
                items(1){
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(imageSize.value)
                            .clickable {
                                imagePagingNumber.value = 2
                            },
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.time_line_next),
                            contentDescription = null
                        )
                    }
                }
            } else if (imagePagingNumber.value == 2) {
                items(1){
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(imageSize.value.div(2))
                            .clickable {
                                imagePagingNumber.value = 3
                            },
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.time_line_next),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }

}

fun getImageDp(
    count: Int,
    screenWidth: Dp
): Dp {
    return if (count > 4) (screenWidth / 360) * 72.5.dp.value
    else screenWidth / count
}