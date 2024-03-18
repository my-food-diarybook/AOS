package com.android.myfooddiarybookaos.home.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.state.AddScreenState
import com.android.myfooddiarybookaos.home.item.ItemDiary
import com.android.myfooddiarybookaos.home.viewModel.HomeViewModel

private const val DAY_OF_WEAK = 7

// 리싸이클러 뷰
@Composable
fun MonthDataView(
    viewUpdate: MutableState<Boolean>,
    todayViewModel: TodayViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val calendarDataList = remember {
        mutableStateOf(todayViewModel.getCustomCalendar())
    }
    if (viewUpdate.value) {
        calendarDataList.value = todayViewModel.getCustomCalendar()
    }
    homeViewModel.homeDiaryList.collectAsState().value

    LazyVerticalGrid(
        columns = GridCells.Fixed(DAY_OF_WEAK),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
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
        },
    )
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun MonthDataPreview() {
    MonthDataView(mutableStateOf(true))
}
