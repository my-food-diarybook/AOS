package com.android.myfooddiarybookaos.data.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import okhttp3.MultipartBody

@Composable
fun rememberDiaryState(
    isSelectedGallery : MutableState<Boolean> = remember{ mutableStateOf(false)},
    isHomeDay: MutableState<Boolean> = remember{ mutableStateOf(false) },
    selectedList : List<MultipartBody.Part> = listOf(),
    showSelectView: MutableState<Boolean> = remember { mutableStateOf(false) },
    currentHomeDay: MutableState<String> = remember { mutableStateOf("") }
) = remember(isSelectedGallery,isHomeDay,selectedList,showSelectView,currentHomeDay){
    DiaryState(
        isSelectedGallery,
        isHomeDay,
        selectedList,
        showSelectView,
        currentHomeDay
    )
}