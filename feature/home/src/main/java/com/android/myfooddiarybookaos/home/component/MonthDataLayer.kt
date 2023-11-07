package com.android.myfooddiarybookaos.Layout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

import com.android.myfooddiarybookaos.data.dataCalendar.repository.CustomCalendarImpl
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.home.item.ItemDiary
import com.android.myfooddiarybookaos.home.viewModel.HomeViewModel
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
                            // 해당 날짜로 이동
                            todayViewModel.setDayDate(newCalendar.dateSet[index].day)
                            if (todayViewModel.getDayDate() != null) {

                            }
                        } else {
                            // 해당 날짜로 새로 생성
                            homeViewModel.diaryState.value?.showSelectView?.value = true
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