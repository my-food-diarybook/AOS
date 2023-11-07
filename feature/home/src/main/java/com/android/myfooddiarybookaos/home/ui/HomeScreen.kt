package com.android.myfooddiarybookaos.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.home.calendar.CalendarLayout
import com.android.myfooddiarybookaos.Layout.TopCalendarLayout

import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.home.viewModel.HomeViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel

@Composable
fun HomeScreen(
    diaryState : DiaryState,
    todayViewModel: TodayViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    //set diary State
    homeViewModel.initDiaryState(diaryState)

    // 업로드 시도
    if (diaryState.isSelectedGallery.value) {
        homeViewModel.makeNewDiary(
            todayViewModel.getCurrentDate(),
            diaryState.multiPartList,
            diaryState = { isUpdate ->
                if (isUpdate) {
                    todayViewModel.getCurrentYearMonth()?.let {
                        homeViewModel.getDiaryList(it)
                    }
                }
            }
        )
        diaryState.isSelectedGallery.value = false
    }
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        // top calendar
        TopCalendarLayout(todayViewModel)

        // mid calendar
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CalendarLayout()
        }

    }
}


@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}
