package com.example.myfooddiarybookaos.Layout

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myfooddiarybookaos.Model.DayDate
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.TabHome.CustomCalendar
import com.example.myfooddiarybookaos.ViewModel.FakeTodayViewModel
import com.example.myfooddiarybookaos.ViewModel.TodayViewModel
import com.example.myfooddiarybookaos.ViewModel.TodayViewModelInterface
import com.example.myfooddiarybookaos.ui.theme.TextBox
import java.util.*
import kotlin.collections.ArrayList

private const val DAY_OF_WEAK = 7

// 리싸이클러 뷰
@SuppressLint("MutableCollectionMutableState")
@Composable
fun MonthDataView(todayViewModel : TodayViewModelInterface) {
    val currentView by remember { mutableStateOf(
        todayViewModel.customCalendar.value!!.dateList
    ) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(DAY_OF_WEAK),
        content = {
            items(currentView.size) { index ->
                DayItem(dayDate = currentView[index], dayClick = {
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
    val isSelected by remember {
        mutableStateOf(dayDate.isSelected)
    }
    val textView by animateColorAsState(
        if (isSelected == 1) colorResource(id = R.color.line_color_deep)
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
                    if (isSelected == 0) {
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