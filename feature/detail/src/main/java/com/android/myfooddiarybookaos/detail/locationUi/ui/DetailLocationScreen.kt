package com.android.myfooddiarybookaos.detail.locationUi.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.detail.state.DetailFixState

@Composable
fun DetailLocationScreen(
    diaryFixState: DetailFixState,
    currentViewState: MutableState<DiaryViewState>
) {
    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {
        currentViewState.value = DiaryViewState.MEMO
    })
}