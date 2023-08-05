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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.Model.DayDate
import com.android.myfooddiarybookaos.R
import com.android.myfooddiarybookaos.TabHome.CustomCalendar
import com.android.myfooddiarybookaos.ViewModel.FakeTodayViewModel
import com.android.myfooddiarybookaos.ViewModel.TodayViewModelInterface
import java.util.*

private const val DAY_OF_WEAK = 7

// 리싸이클러 뷰
@SuppressLint("MutableCollectionMutableState")
@Composable
fun MonthDataView(todayViewModel : TodayViewModelInterface) {
    // 현재 뷰어
    val viewCalendar : State<Boolean> = todayViewModel.dataChanger.observeAsState(false)
    if (viewCalendar.value){
        ItemScreen(date = todayViewModel.currentCalendar.value!!.time)
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
        modifier = Modifier.clickable(
            onClick = {
                dayClick(dayDate.day)
            })
    ) {
            Box(
                Modifier.then(
                    if (dayDate.isSelected == 0) {
                        Modifier
                            .background(
                                colorResource(id = R.color.main_color),
                                CircleShape
                            )
                            .size(dimensionResource(id = R.dimen.size_40))
                    }else{ // 나중에 이미지 여기에 추가 !!
                        Modifier
                    }
                )
            )

        if (dayDate.day != 0) {
            Text(
                text = dayDate.day.toString(),
                fontWeight = FontWeight(400),
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontSize = dimensionResource(id = R.dimen.size_12_sp).value.sp,
                color = textView,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.size_20),
                    bottom = dimensionResource(id = R.dimen.size_20),
                    start = dimensionResource(id = R.dimen.size_12),
                    end = dimensionResource(id = R.dimen.size_12),
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MonthDataPreview() {
    MonthDataView(FakeTodayViewModel())
}