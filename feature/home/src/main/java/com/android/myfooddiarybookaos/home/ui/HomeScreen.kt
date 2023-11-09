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
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.home.ui.HomeDayScreen

@Composable
fun HomeScreen(
    diaryState : DiaryState,
    appState: ApplicationState,
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

    // main view
    Column {
        val homeDayDate = todayViewModel.getDayDate()
        if (diaryState.isHomeDay.value && homeDayDate!=null){
            HomeDayScreen(currentDate = homeDayDate)
        } else {
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
}


@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}
