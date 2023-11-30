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
import com.android.myfooddiarybookaos.data.state.AddScreenState
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

@Composable
fun HomeScreen(
    diaryState: DiaryState,
    appState: ApplicationState,
    todayViewModel: TodayViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    //set diary State
    LaunchedEffect(Unit) {
        homeViewModel.initState(appState, diaryState)
    }

    // 업로드 시도
    if (diaryState.isSelectedGallery.value) {
        Log.d("sdlfjsdljsdlfsjdldsfj",diaryState.multiPartList.toString())
        Log.d("sdlfjsdljsdlfsjdldsfj",diaryState.addScreenState.toString())
        when (diaryState.addScreenState.value){
            AddScreenState.ADD_HOME_TODAY -> {
                homeViewModel.makeNewDiary(
                    todayViewModel.getTodayDate(),
                    diaryState.multiPartList,
                    diaryState = { isUpdate ->
                        if (isUpdate) {
                            todayViewModel.getCurrentYearMonth()?.let {
                                homeViewModel.getDiaryList(it)
                            }
                        }
                    }
                )
            }
            AddScreenState.ADD_NO_DATA_DAY -> {
                homeViewModel.makeNewDiary(
                    diaryState.currentHomeDay.value,
                    diaryState.multiPartList,
                    diaryState = { isUpdate ->
                        if (isUpdate) {
                            todayViewModel.getCurrentYearMonth()?.let {
                                homeViewModel.getDiaryList(it)
                            }
                        }
                    }
                )
                diaryState.resetHomeDay()
            }
            else -> {}
        }
        diaryState.resetSelectedInfo()
    }

    // main view
    Column {
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
