package com.android.myfooddiarybookaos.home.calendar


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.myfooddiarybookaos.Layout.MonthDataView

import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModel

private const val DAY_OF_WEAK = 7

@Composable
fun CalendarLayout(
    todayViewModel : TodayViewModel = viewModel()
){
    Column(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        // 요일 뷰
        val dayList = listOf("S", "M", "T", "W", "T", "F", "S")
        LazyVerticalGrid(
            columns = GridCells.Fixed(DAY_OF_WEAK),
            content = {
                items(DAY_OF_WEAK) { index ->
                    DayLayer(text = dayList[index])
                }
            }
        )
        // 캘린더
        MonthDataView(todayViewModel)
    }
}


// 일 별 레이어
@Composable
private fun DayLayer(text: String){
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(
                horizontal = 12.86.dp
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontFamily = robotoRegular,
            fontWeight = FontWeight(400),
            color = colorResource(id = R.color.color_day_of_weak),
        )
    }
}

