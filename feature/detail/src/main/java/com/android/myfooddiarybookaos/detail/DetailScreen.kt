package com.android.myfooddiarybookaos.detail

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.android.myfooddiarybookaos.data.state.ApplicationState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.state.AddScreenState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.detail.locationUi.ui.DetailLocationScreen
import com.android.myfooddiarybookaos.detail.mainUi.ui.DetailMemoScreen
import com.android.myfooddiarybookaos.detail.mainUi.ui.MainDetailScreen
import com.android.myfooddiarybookaos.data.state.DetailFixState
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel

@Composable
fun DetailScreen(
    appState: ApplicationState,
    diaryState: DiaryState,
    diaryFixState: DetailFixState,
    detailViewModel: DetailViewModel = hiltViewModel(),
    todayViewModel: TodayViewModel = hiltViewModel()
) {
    BackHandler(enabled = true, onBack = {
        detailViewModel.goBack()
    })

    val diaryDetail = detailViewModel.diaryDetail.value
    val topDate = todayViewModel.getTopDate(diaryDetail?.date)
    // diary state
    val currentViewState = remember { mutableStateOf(DiaryViewState.MAIN) }

    LaunchedEffect(Unit) {
        detailViewModel.initAppState(appState, diaryState)
        detailViewModel.setDiaryDetail(
            initData = {
                diaryFixState.initMemo(it)
            }
        )
    }

    // 현재 뷰 상태
    when (currentViewState.value) {
        DiaryViewState.MAIN -> {
            MainDetailScreen(
                diaryState,
                topDate,
                initMemo = {
                    diaryFixState.initMemo(diaryDetail)
                },
                currentViewState
            )
        }
        DiaryViewState.MEMO -> {
            DetailMemoScreen(diaryFixState, currentViewState)
        }
        DiaryViewState.LOCATION -> {
            DetailLocationScreen(diaryFixState, currentViewState)
        }
    }

}