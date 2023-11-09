package com.android.myfooddiarybookaos.data.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import okhttp3.MultipartBody

@Stable
class DiaryState(
    val isSelectedGallery : MutableState<Boolean>,
    var multiPartList : List<MultipartBody.Part>,
    val showSelectView: MutableState<Boolean>,
    val isHomeDay: MutableState<Boolean>
) {}