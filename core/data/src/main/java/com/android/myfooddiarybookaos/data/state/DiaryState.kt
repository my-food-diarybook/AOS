package com.android.myfooddiarybookaos.data.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import okhttp3.MultipartBody

@Stable
class DiaryState(
    val isSelectedGallery: MutableState<Boolean>,
    val isViewUpdate : MutableState<Boolean>,
    var multiPartList: List<MultipartBody.Part>,
    val showSelectView: MutableState<Boolean>,
    val currentHomeDay: MutableState<String>,
    val addScreenState: MutableState<AddScreenState>,
    val currentDiaryDetail: MutableState<Int>,
    val fixImageId: MutableState<Int>
) {
    fun resetSelectedInfo() {
        isSelectedGallery.value = false
        multiPartList = listOf()
    }

    fun resetHomeDay() {
        currentHomeDay.value = ""
    }

    fun setHomeDay(day: String?) {
        day?.let { currentHomeDay.value = day }
    }

    fun setDiaryDetail(id: Int) {
        currentDiaryDetail.value = id
    }

    fun resetDiaryDetail() {
        currentDiaryDetail.value = -1
    }

    fun setFixImageId(id: Int) {
        fixImageId.value = id
    }

    fun updateView(){
        isViewUpdate.value = true
    }
}
