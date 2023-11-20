package com.android.myfooddiarybookaos.detail.locationUi.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.detail.state.DetailFixState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.detail.locationUi.component.CurrentLocationLayer
import com.android.myfooddiarybookaos.detail.locationUi.component.LocationTopLayer
import com.android.myfooddiarybookaos.detail.locationUi.component.SearchResultLayer
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailLocationScreen(
    diaryFixState: DetailFixState,
    currentViewState: MutableState<DiaryViewState>,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity
    var userInput by remember { mutableStateOf("") }
    val submitEnabled = remember { derivedStateOf { userInputValid(userInput) } }
    val currentLocationResult = detailViewModel.currentLocationResult.observeAsState()
    val searchResult = detailViewModel.searchResult.observeAsState()
    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {
        currentViewState.value = DiaryViewState.MEMO
    })

    LaunchedEffect(Unit) {
        detailViewModel.setMyLocation(activity)
    }

    Column {
        LocationTopLayer(
            userInput,
            submitEnabled,
            search = {
                userInput = it
                coroutineScope.launch {// 로드 딜레이
                    delay(100)
                    detailViewModel.getSearchResult(userInput)
                }
            },
            goBack = {
                currentViewState.value = DiaryViewState.MEMO
            },
            onDelete = {
                userInput = ""
            }
        )

        if (submitEnabled.value) {
            SearchResultLayer(userInput, searchResult)
        } else {
            CurrentLocationLayer(currentLocationResult)
        }
    }

}

private fun userInputValid(userInput: String) = userInput.isNotEmpty()