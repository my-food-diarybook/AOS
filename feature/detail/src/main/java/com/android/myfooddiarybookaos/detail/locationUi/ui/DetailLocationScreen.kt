package com.android.myfooddiarybookaos.detail.locationUi.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.detail.state.DetailFixState
import androidx.compose.runtime.*
import com.android.myfooddiarybookaos.detail.locationUi.component.CurrentLocationLayer
import com.android.myfooddiarybookaos.detail.locationUi.component.LocationTopLayer
import com.android.myfooddiarybookaos.detail.locationUi.component.SearchResultLayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailLocationScreen(
    diaryFixState: DetailFixState,
    currentViewState: MutableState<DiaryViewState>
) {
    val coroutineScope = rememberCoroutineScope()
    var userInput by remember { mutableStateOf("") }
    val submitEnabled = remember { derivedStateOf { userInputValid(userInput) } }

    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {
        currentViewState.value = DiaryViewState.MEMO
    })

    Column {
        LocationTopLayer(
            userInput,
            submitEnabled,
            search = {
                userInput = it
            },
            goBack = {
                currentViewState.value = DiaryViewState.MEMO
            }
        )

        if (submitEnabled.value) {
            SearchResultLayer(userInput)
        } else {
            CurrentLocationLayer()
        }
    }

}

private fun userInputValid(userInput: String) = userInput.isNotEmpty()