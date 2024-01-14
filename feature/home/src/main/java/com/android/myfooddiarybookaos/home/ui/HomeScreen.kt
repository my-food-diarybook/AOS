package com.android.myfooddiarybookaos.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.component.ToastMessaging
import com.android.myfooddiarybookaos.home.component.CalendarLayout
import com.android.myfooddiarybookaos.data.component.TopCalendarLayout
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel

import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.home.viewModel.HomeViewModel
import com.android.myfooddiarybookaos.data.state.AddScreenState
import com.android.myfooddiarybookaos.data.state.ApplicationState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        homeViewModel.setDiaryList(todayViewModel.getCurrentYearMonth())
    }

    val viewUpdate = rememberSaveable { mutableStateOf(true) }
    if (viewUpdate.value) {
        homeViewModel.setDiaryList(todayViewModel.getCurrentYearMonth())
        rememberCoroutineScope().launch {
            delay(500)
            viewUpdate.value = false
        }
    }

    // 업로드 시도
    if (diaryState.isSelectedGallery.value) {
        when (diaryState.addScreenState.value) {
            AddScreenState.ADD_HOME_TODAY -> {
                homeViewModel.makeNewDiary(
                    todayViewModel.getTodayDate(),
                    diaryState.multiPartList,
                    diaryState = { isUpdate ->
                        if (isUpdate) {
                            homeViewModel.setDiaryList(todayViewModel.getCurrentYearMonth())
                        }
                    },
                    toastMessage = {
                        appState.toastState.value =  "하루에 식사일기는 최대10건까지 등록할 수 있어요."
                    }
                )
            }
            AddScreenState.ADD_NO_DATA_DAY -> {
                homeViewModel.makeNewDiary(
                    diaryState.currentHomeDay.value,
                    diaryState.multiPartList,
                    diaryState = { isUpdate ->
                        if (isUpdate) {
                            todayViewModel.getCurrentYearMonth().let {
                                homeViewModel.setDiaryList(todayViewModel.getCurrentYearMonth())
                            }
                        }
                    },
                    toastMessage = {
                        appState.toastState.value =  "하루에 식사일기는 최대10건까지 등록할 수 있어요."
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
        TopCalendarLayout(
            resetData = {
                viewUpdate.value = true
            },
            isMainView = true
        )
        // mid calendar
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CalendarLayout(viewUpdate)
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}
