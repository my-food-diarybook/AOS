package com.android.myfooddiarybookaos.home.component

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.state.AddScreenState
import com.android.myfooddiarybookaos.home.item.ItemDiary
import com.android.myfooddiarybookaos.home.viewModel.HomeViewModel
import com.android.myfooddiarybookaos.model.DayDate
import com.android.myfooddiarybookaos.model.diary.Diary

private const val DAY_OF_WEAK = 7

// 리싸이클러 뷰
@Composable
fun MonthDataView(
    todayViewModel: TodayViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val calendarDataList = remember {
        mutableStateOf(todayViewModel.getCustomCalendar())
    }
    todayViewModel.getDataChange().observeAsState().value?.let {
        calendarDataList.value = todayViewModel.getCustomCalendar()
    }
    homeViewModel.homeDiaryList.collectAsState().value

    LazyVerticalGrid(
        columns = GridCells.Fixed(DAY_OF_WEAK),
        content = {
            items(calendarDataList.value.size) { index ->
                val currentDiary = homeViewModel.getCurrentDiary(
                    todayViewModel.getCurrentYearMonth(),
                    calendarDataList.value[index].day.toString()
                )
                ItemDiary(
                    dayDate = calendarDataList.value[index],
                    dayClick = {
                        val dayDate = todayViewModel.getDayDate(calendarDataList.value[index].day)
                        homeViewModel.diaryState.value?.setHomeDay(dayDate)
                        if (currentDiary != null) {
                            // 해당 날짜로 이동
                            homeViewModel.goHomeDayView()
                        } else {
                            // 해당 날짜로 새로 생성
                            homeViewModel.diaryState.value?.apply {
                                addScreenState.value = AddScreenState.ADD_NO_DATA_DAY
                                showSelectView.value = true
                            }
                        }
                    },
                    imageByte = currentDiary?.bytes
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