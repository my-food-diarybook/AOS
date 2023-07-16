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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import java.util.Calendar

@Composable
fun CalendarLayout(customCalendar : CustomCalendar){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ){
            // 요일 적용
            for (day in listOf("S","M","T","W","T","F","S")){
                DayLayer(text = day)
            }
        }
        Log.d("Custom call list",customCalendar.dateList.toString())
        MonthDataView(dayList = customCalendar.dateList)
    }
}


// 일 별 레이어
@Composable
private fun DayLayer(text: String){
    Text(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        text = text,
        fontSize = dimensionResource(id = R.dimen.size_12_sp).value.sp,
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        color = colorResource(id = R.color.color_day_of_weak),
    )
}

// 리싸이클러 뷰
@Composable
private fun MonthDataView(dayList : ArrayList<DayDate> ){
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        content = {
            items(dayList.size){index->
                DayItem(dayDate = dayList[index])
            }
        }
    )
}
@Composable
private fun DayItem(dayDate: DayDate){
    Log.d("print dayDate ",dayDate.day.toString())
    val isSelected by remember {
        mutableStateOf(dayDate.isSelected)
    }
    val textView by animateColorAsState(
        if (isSelected==1) colorResource(id = R.color.line_color_deep)
        else colorResource(id = R.color.color_day_of_weak)
    )
    Surface(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.size_40))
            .height(dimensionResource(id = R.dimen.size_40))
            .then(
                if (isSelected == 0) {
                    Modifier.background(
                        color = colorResource(id = R.color.main_color),
                        shape = CircleShape
                    )
                } else {
                    Modifier
                }
            ),
    ) {
        TextBox(
            text = dayDate.day.toString(),
            fontWeight = 400 ,
            fontFamily = Font(R.font.roboto_bold),
            fontSize = dimensionResource(id = R.dimen.size_12_sp).value.sp,
            color = textView,

        )
    }
}

@Preview
@Composable
private fun CalendarLayoutPreview(){
    val calendarDate = Calendar.getInstance()
    val customCalendar  = CustomCalendar(calendarDate.time)
    CalendarLayout(customCalendar)
}
