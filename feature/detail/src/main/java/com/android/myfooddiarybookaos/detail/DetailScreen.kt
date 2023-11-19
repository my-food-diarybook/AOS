package com.android.myfooddiarybookaos.detail

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.data.state.ApplicationState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.dataCalendar.viewModel.TodayViewModel
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.detail.mainUi.component.*
import com.android.myfooddiarybookaos.detail.mainUi.imageSlider.ImageSliderScreen
import com.android.myfooddiarybookaos.detail.mainUi.ui.MainDetailScreen
import com.android.myfooddiarybookaos.detail.state.rememberDiaryFixState
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel
import com.android.myfooddiarybookaos.model.detail.Tag


@Composable
fun DetailScreen(
    appState: ApplicationState,
    diaryState: DiaryState,
    detailViewModel: DetailViewModel = hiltViewModel(),
    todayViewModel: TodayViewModel = hiltViewModel()
) {
    BackHandler(enabled = true, onBack = {
        detailViewModel.goBack()
    })

    val diaryDetail = detailViewModel.diaryDetail.observeAsState().value
    val topDate = todayViewModel.getTopDate(diaryDetail?.date)
    val currentViewState = remember {
        mutableStateOf(DiaryViewState.MAIN)
    }
    // diary state
    val diaryFixState = rememberDiaryFixState(diaryDetail = diaryDetail)

    LaunchedEffect(Unit) {
        detailViewModel.initAppState(appState, diaryState)
        detailViewModel.setDiaryDetail()
    }

    Column {

        when(currentViewState.value){
            DiaryViewState.MAIN -> {
                MainDetailScreen(
                    topDate
                    ,diaryDetail
                )
            }
            DiaryViewState.MEMO -> {

            }
            DiaryViewState.LOCATION -> {

            }
        }

    }
}