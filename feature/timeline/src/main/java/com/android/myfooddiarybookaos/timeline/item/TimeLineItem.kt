package com.android.myfooddiarybookaos.timeline.item

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
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

    LaunchedEffect(Unit) {
        viewModel.initTimeLiseData(timeLine.diaryList)
    }

    val imageSize = remember {
        mutableStateOf(
            getImageDp(timeLine.diaryList.size, screenWidth)
        )
    }
    val imagePagingNumber = remember {
        mutableStateOf(0)
    }
    val timeLineData = viewModel.timeLineDiary.collectAsState()
    when(imagePagingNumber.value){
        1-> {
            viewModel.setTimeLineData(timeLine.date,5)
        }
        2 -> {
            viewModel.setTimeLineData(timeLine.date,9)
        }
        else ->{
            viewModel.initTimeLiseData(timeLine.diaryList)
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
            if (imagePagingNumber.value == 1){

            } else if (imagePagingNumber.value == 2){

            }

            items(timeLineData.value) { diary ->
                val imageBitmap = remember { mutableStateOf(byteStringToBitmap(diary.bytes)) }
                ImageItem(
                    imageBitmap = imageBitmap,
                    imageSize = imageSize.value,
                    onClick = {
                        timeLineViewModel.goDetailView(diary.diaryId)
                    }
                )
            }
            if (imagePagingNumber.value == 0){

            } else if (imagePagingNumber.value == 1){

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