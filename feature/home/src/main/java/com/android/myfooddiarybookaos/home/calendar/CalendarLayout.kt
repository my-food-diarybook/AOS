package com.android.myfooddiarybookaos.home.calendar


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.Layout.MonthDataView

import com.android.myfooddiarybookaos.feature.home.R

private const val DAY_OF_WEAK = 7

@Composable
fun CalendarLayout(todayViewModel : com.android.myfooddiarybookaos.data.viewModel.TodayViewModelInterface){
    Column {
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
            .padding(
                top = dimensionResource(id = R.dimen.size_20),
                bottom = dimensionResource(id = R.dimen.size_20),
                start = dimensionResource(id = R.dimen.size_12),
                end = dimensionResource(id = R.dimen.size_12),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontSize = dimensionResource(id = R.dimen.size_12_sp).value.sp,
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            color = colorResource(id = R.color.color_day_of_weak),
        )
    }
}

