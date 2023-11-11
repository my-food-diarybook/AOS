package com.android.myfooddiarybookaos.Layout

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.home.item.ItemDiary
import com.android.myfooddiarybookaos.home.viewModel.HomeViewModel
import com.android.myfooddiarybookaos.model.DayDate
import com.android.myfooddiarybookaos.model.diary.Diary
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import java.util.*

private const val DAY_OF_WEAK = 7

// 리싸이클러 뷰
@SuppressLint("MutableCollectionMutableState")
@Composable
fun MonthDataView(
    todayViewModel: TodayViewModel = hiltViewModel()
) {
    // 현재 뷰어
    val viewCalendar: State<Boolean> = todayViewModel
        .todayRepository.dataChanger.observeAsState(false)

    if (viewCalendar.value) {
        todayViewModel.todayRepository.currentCalendar.value?.let {
            ItemScreen(todayViewModel.getCustomCalendar())
        }
    }

}

@Composable
fun ItemScreen(
    calendarDataList : List<DayDate>,
    todayViewModel: TodayViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    // 다이어리 변화 관찰
    var currentDiaryList: List<Diary> = listOf()
    homeViewModel.homeDiaryList.observeAsState().value?.let {
        currentDiaryList = it
    }
    val yearMonth = todayViewModel.getCurrentYearMonth()
    LaunchedEffect(Unit) {
        yearMonth?.let {
            homeViewModel.getDiaryList(it)
        }
    }
    Log.d("lfjwljelwf12341l2j4l12",calendarDataList.map { it.isSelected }.toString())

    LazyVerticalGrid(
        columns = GridCells.Fixed(DAY_OF_WEAK),
        content = {
            items(calendarDataList.size) { index ->
                val imageByte = homeViewModel.getByteString(
                    yearMonth, calendarDataList[index].day.toString()
                )
                ItemDiary(
                    dayDate = calendarDataList[index],
                    dayClick = {
                        val dayDate = todayViewModel.getDayDate(calendarDataList[index].day)
                        dayDate?.let { homeViewModel.diaryState.value?.currentHomeDay?.value = it }
                        if (imageByte != null) {
                            // 해당 날짜로 이동
                            homeViewModel.appState.value?.navController?.navigate(ScreenRoot.HOME_DAY)
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