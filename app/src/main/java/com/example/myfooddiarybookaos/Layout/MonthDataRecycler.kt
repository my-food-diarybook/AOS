package com.example.myfooddiarybookaos.Layout

import android.annotation.SuppressLint
import android.util.Log
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
import com.example.myfooddiarybookaos.Model.DayDate
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import com.example.myfooddiarybookaos.ViewModel.FakeTodayViewModel
import com.example.myfooddiarybookaos.ViewModel.TodayViewModelInterface
import java.util.*

private const val DAY_OF_WEAK = 7

// 리싸이클러 뷰
@SuppressLint("MutableCollectionMutableState")
@Composable
fun MonthDataView(todayViewModel : TodayViewModelInterface) {
    // 현재 뷰어
    val viewCalendar : State<CustomCalendar?> = todayViewModel.customCalendar.observeAsState()
//    val dataList = List(dateSet.size) { i -> DayDate(dateSet[i].day,dateSet[i].isSelected) }
    viewCalendar.value?.let {
        ItemScreen(it.dateSet)
    }
}

@Composable
fun ItemScreen(data : List<DayDate>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(DAY_OF_WEAK),
        content = {
            items(data.size) { index ->
                DayItem(dayDate = data[index], dayClick = {
                    // 일 클릭 이벤트
                    for (i in data){
                        Log.d("sdfwlfh",i.day.toString())
                    }
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