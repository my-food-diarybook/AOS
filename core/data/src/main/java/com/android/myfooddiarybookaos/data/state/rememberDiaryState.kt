package com.android.myfooddiarybookaos.data.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import okhttp3.MultipartBody

@Composable
fun rememberDiaryState(

    isSelectedGallery: MutableState<Boolean> = remember { mutableStateOf(false) },
    isUpdateView: MutableState<Boolean> = remember { mutableStateOf(false) },
    selectedList: List<MultipartBody.Part> = listOf(),
    showSelectView: MutableState<Boolean> = remember { mutableStateOf(false) },
    currentHomeDay: MutableState<String> = remember { mutableStateOf("") },
    addScreenState: MutableState<AddScreenState> = remember { mutableStateOf(AddScreenState.ADD_HOME_TODAY) },
    currentDiaryDetail: MutableState<Int> = remember { mutableStateOf(-1) },
    fixImageId: MutableState<Int> = remember { mutableStateOf(-1) }

) = remember(
    isSelectedGallery,
    isUpdateView,
    selectedList,
    showSelectView,
    currentHomeDay,
    addScreenState,
    currentDiaryDetail,
    fixImageId
) {

    DiaryState(
        isSelectedGallery,
        isUpdateView,
        selectedList,
        showSelectView,
        currentHomeDay,
        addScreenState,
        currentDiaryDetail,
        fixImageId
    )
}
