package com.android.myfooddiarybookaos.Layout

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.robotoBold

import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.home.calendar.CustomCalendar
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.model.DayDate
import java.util.*

private const val DAY_OF_WEAK = 7

// 리싸이클러 뷰
@SuppressLint("MutableCollectionMutableState")
@Composable
fun MonthDataView(
    todayViewModel: TodayViewModel = hiltViewModel()
) {
    // 현재 뷰어
    val viewCalendar : State<Boolean> = todayViewModel
        .todayRepository.dataChanger.observeAsState(false)

    if (viewCalendar.value){
        ItemScreen(date = todayViewModel
            .todayRepository.currentCalendar.value!!.time)
    }
}

@Composable
fun ItemScreen(date : Date){
    val newCalendar = CustomCalendar()
    newCalendar.initData(date)
    LazyVerticalGrid(
        columns = GridCells.Fixed(DAY_OF_WEAK),
        content = {
            items(newCalendar.dateSet.size) { index ->
                DayItem(dayDate = newCalendar.dateSet[index], dayClick = {
                    // 일 클릭 이벤트

                })
            }
        }
    )
}

@Composable
fun DayItem(
    dayDate: DayDate,
    dayClick : (Int) -> Unit
) {

    val textView by animateColorAsState(
        if (dayDate.isSelected == 1) colorResource(id = R.color.line_color_deep)
        else colorResource(id = R.color.color_day_of_weak)
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(
                onClick = {
                    dayClick(dayDate.day)
                })
            .padding(
                vertical = 12.86.dp,
                horizontal = 2.29.dp
            )
            .aspectRatio(1f)
    ) {
        Box(
            Modifier
                .then(
                    if (dayDate.isSelected == 0) {
                        Modifier
                            .background(
                                colorResource(id = R.color.main_color),
                                CircleShape
                            )
                            .size(40.dp)
                    } else { // 나중에 이미지 여기에 추가 !!
                        Modifier
                    }
                )
        )

        if (dayDate.day != 0) {
            Text(
                text = dayDate.day.toString(),
                fontWeight = FontWeight(400),
                fontFamily = robotoBold,
                fontSize = 12.sp,
                color = textView,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MonthDataPreview() {
    MonthDataView()
}