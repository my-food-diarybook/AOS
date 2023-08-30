package com.android.myfooddiarybookaos.home.calendar


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.myfooddiarybookaos.Layout.MonthDataView

import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.viewModel.TodayViewModelInterface

private const val DAY_OF_WEAK = 7

@Composable
fun CalendarLayout(
    todayViewModel : TodayViewModel = viewModel()
){
    val oneWidth = LocalConfiguration.current.screenWidthDp.dp / 7
    val oneHeight = LocalConfiguration.current.screenHeightDp.dp*(460/800) / 7
    Column {
        // 요일 뷰
        val dayList = listOf("S", "M", "T", "W", "T", "F", "S")
        LazyVerticalGrid(
            columns = GridCells.Fixed(DAY_OF_WEAK),
            content = {
                items(DAY_OF_WEAK) { index ->
                    DayLayer(text = dayList[index],oneWidth,oneHeight)
                }
            }
        )
        // 캘린더
        MonthDataView(todayViewModel)
    }
}


// 일 별 레이어
@Composable
private fun DayLayer(
    text: String,
    oneWidth : Dp,
    oneHeight : Dp
){
    Box(
        modifier = Modifier
            .width(oneWidth)
            .height(oneHeight)
        ,contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontSize = dimensionResource(id = R.dimen.size_12_sp).value.sp,
            fontFamily = robotoRegular,
            fontWeight = FontWeight(400),
            color = colorResource(id = R.color.color_day_of_weak),
        )
    }
}

