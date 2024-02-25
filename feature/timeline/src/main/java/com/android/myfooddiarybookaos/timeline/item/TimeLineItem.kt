package com.android.myfooddiarybookaos.timeline.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.android.myfooddiarybookaos.model.timeLine.TimeLineDiary
import com.android.myfooddiarybookaos.timeline.viewModel.ItemViewModel
import com.android.myfooddiarybookaos.timeline.viewModel.TimeLineViewModel

@Composable
fun TimeLineItem(
    todayViewModel: TodayViewModel = hiltViewModel(),
    timeLineViewModel: TimeLineViewModel = hiltViewModel(),
    viewModel: ItemViewModel = hiltViewModel(),
    timeLine: TimeLine,
    screenWidth: Dp
) {

    val imageSize = remember { mutableStateOf(getImageDp(timeLine.diaryList.size, screenWidth)) }
    val imagePagingNumber = remember { mutableStateOf(if (timeLine.diaryList.size < 5) 0 else 1) }
    val timeLineData = remember { mutableListOf<TimeLineDiary>() }
    val viewUpdate = remember { mutableStateOf(true) }

    if (viewUpdate.value) {
        when (imagePagingNumber.value) {
            2 -> {
                LaunchedEffect(Unit) {
                    viewModel.setTimeLineData(timeLine.date, 1, diaryList = {
                        val last = timeLineData.lastOrNull()
                        timeLineData.clear()
                        if (last != null) {
                            timeLineData.add(last)
                        }
                        timeLineData.addAll(it)
                        viewUpdate.value = false
                    })
                }
            }

            3 -> {
                LaunchedEffect(Unit) {
                    viewModel.setTimeLineData(timeLine.date, 2, diaryList = {
                        val last = timeLineData.lastOrNull()
                        timeLineData.clear()
                        if (last != null) {
                            timeLineData.add(last)
                        }
                        timeLineData.addAll(it)
                        viewUpdate.value = false
                    })
                }
            }

            else -> {
                LaunchedEffect(Unit) {
                    timeLineData.clear()
                    timeLineData.addAll(timeLine.diaryList)
                    viewUpdate.value = false
                }
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
            if (!viewUpdate.value) {
                if (imagePagingNumber.value == 2) {
                    items(1) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(
                                    if (timeLineData.size > 4) imageSize.value.div(2)
                                    else imageSize.value
                                )
                                .clickable {
                                    imagePagingNumber.value =
                                        if (timeLine.diaryList.size < 5) 0 else 1
                                    viewUpdate.value = true
                                },
                            contentAlignment = Alignment.Center
                        ) {
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
                                    viewUpdate.value = true
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
                        val imageBitmap =
                            remember { mutableStateOf(byteStringToBitmap(diary.bytes)) }
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
                    items(1) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(imageSize.value)
                                .clickable {
                                    imagePagingNumber.value = 2
                                    viewUpdate.value = true
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.time_line_next),
                                contentDescription = null
                            )
                        }
                    }
                } else if (imagePagingNumber.value == 2 && timeLineData.size > 4) {
                    items(1) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(imageSize.value.div(2))
                                .clickable {
                                    imagePagingNumber.value = 3
                                    viewUpdate.value = true
                                },
                            contentAlignment = Alignment.Center
                        ) {
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

}

fun getImageDp(
    count: Int,
    screenWidth: Dp
): Dp {
    return if (count > 4) (screenWidth / 360) * 72.5.dp.value
    else screenWidth / count
}
