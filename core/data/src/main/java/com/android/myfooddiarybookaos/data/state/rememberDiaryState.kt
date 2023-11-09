package com.android.myfooddiarybookaos.data.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import okhttp3.MultipartBody

@Composable
fun rememberDiaryState(
    isSelectedGallery : MutableState<Boolean> = remember{ mutableStateOf(false)},
    selectedList : List<MultipartBody.Part> = listOf(),
    showSelectView: MutableState<Boolean> = remember { mutableStateOf(false) },
    isHomeDay: MutableState<Boolean> = remember { mutableStateOf(false) }
) = remember(isSelectedGallery,selectedList,showSelectView){
    DiaryState(
        isSelectedGallery,
        selectedList,
        showSelectView,
        isHomeDay
    )
}