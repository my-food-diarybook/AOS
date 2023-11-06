package com.android.myfooddiarybookaos.Layout

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.robotoBold

import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.dataCalendar.repository.CustomCalendarImpl
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.home.item.ItemDiary
import com.android.myfooddiarybookaos.home.viewModel.HomeViewModel
import com.android.myfooddiarybookaos.model.DayDate
import com.android.myfooddiarybookaos.model.diary.Diary
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
fun ItemScreen(
    date : Date,
    todayViewModel: TodayViewModel = hiltViewModel(),
    homeViewModel : HomeViewModel = hiltViewModel()
){
    var currentDiaryList: List<Diary> = listOf()
    // 다이어리 변화 관찰
    homeViewModel.homeDiaryList.observeAsState().value?.let {
        currentDiaryList = it
    }
    LaunchedEffect(Unit){
        todayViewModel.getCurrentYearMonth()?.let {
            homeViewModel.getDiaryList(it)
        }
    }

    val newCalendar = CustomCalendarImpl()
    newCalendar.initData(date)
    LazyVerticalGrid(
        columns = GridCells.Fixed(DAY_OF_WEAK),
        content = {
            items(newCalendar.dateSet.size) { index ->
                val imageByte = homeViewModel.getByteString(
                    todayViewModel.getCurrentYearMonth(),
                    newCalendar.dateSet[index].day.toString()
                )
                ItemDiary(
                    dayDate = newCalendar.dateSet[index],
                    dayClick = {

                        if (imageByte != null) {

                        } else {
                            // 해당 날짜로 새로 생성
                        }
                    },
                    imageByte = imageByte
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MonthDataPreview() {
    MonthDataView()
}