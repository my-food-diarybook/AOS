package com.example.myfooddiarybookaos.Layout


import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myfooddiarybookaos.Model.DayDate
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.ui.theme.TextBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import com.example.myfooddiarybookaos.Model.TabHome.CustomCalendar
import java.util.Calendar
private const val DAY_OF_WEAK = 7

@Composable
fun CalendarLayout(customCalendar : CustomCalendar){
    Column {

        val dayList = listOf("S", "M", "T", "W", "T", "F", "S")
        LazyVerticalGrid(
            columns = GridCells.Fixed(DAY_OF_WEAK),
            content = {
                items(DAY_OF_WEAK) { index ->
                    DayLayer(text = dayList[index])
                }
            }
        )
        MonthDataView(dayList = customCalendar.dateList)

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

