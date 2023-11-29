package com.android.myfooddiarybookaos.detail.locationUi.ui

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.data.state.DetailFixState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.detail.locationUi.component.CurrentLocationLayer
import com.android.myfooddiarybookaos.detail.locationUi.component.LocationTopLayer
import com.android.myfooddiarybookaos.detail.locationUi.component.SearchResultLayer
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel
import com.android.myfooddiarybookaos.model.map.Place
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailLocationScreen(
    diaryFixState: DetailFixState,
    currentViewState: MutableState<DiaryViewState>,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var userInput by remember { mutableStateOf("") }
    var prevInput by remember { mutableStateOf("") }
    var currentLoad by remember { mutableStateOf(false) }
    val searchUpdate = remember { derivedStateOf { userInputUpdate(userInput,prevInput) }}
    val submitEnabled = remember { derivedStateOf { userInputValid(userInput) } }
    val currentLocationResult = detailViewModel.currentLocationResult.observeAsState()
    val searchResult = detailViewModel.searchResult.observeAsState()

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        /** 권한 요청시 동의 했을 경우 **/
        if (areGranted) {
            currentLoad = true
        }
        /** 권한 요청시 거부 했을 경우 **/
//        else {
//
//        }
    }
    if (currentLoad){
        detailViewModel.setMyLocation()
        currentLoad = false
    }

    fun setSelectLocation(place: Place) {
        try {
            diaryFixState.place.value = place.place_name
            diaryFixState.longitude.value = place.x.toDouble()
            diaryFixState.latitude.value = place.y.toDouble()
        } catch (_:Exception){ }
    }

    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {
        currentViewState.value = DiaryViewState.MEMO
    })

    LaunchedEffect(Unit) {
        detailViewModel.requestPermission(
            context,
            launcherMultiplePermissions,
            permissionResult = {
                currentLoad = true
            }
        )
    }

    Column {
        LocationTopLayer(
            userInput,
            submitEnabled,
            search = {
                userInput = it
                coroutineScope.launch {// 로드 딜레이
                    delay(500)
                    prevInput = it
                    if (searchUpdate.value) detailViewModel.getSearchResult(userInput)
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
            SearchResultLayer(
                userInput,
                searchResult,
                selectedLocation = {
                    setSelectLocation(it)
                    currentViewState.value = DiaryViewState.MEMO
                }
            )
        } else {
            CurrentLocationLayer(
                currentLocationResult,
                selectedLocation = {
                    setSelectLocation(it)
                    currentViewState.value = DiaryViewState.MEMO
                }
            )
        }
    }

}

private fun userInputValid(userInput: String) = userInput.isNotEmpty()
private fun userInputUpdate(userInput: String,prevInput:String) = userInput == prevInput
