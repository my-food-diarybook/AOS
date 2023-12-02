package com.android.myfooddiarybookaos.detail.mainUi.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.state.AddScreenState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.detail.mainUi.component.DetailData
import com.android.myfooddiarybookaos.detail.mainUi.component.DetailMenuTime
import com.android.myfooddiarybookaos.detail.mainUi.component.DetailTopLayer
import com.android.myfooddiarybookaos.detail.mainUi.imageSlider.ImageSliderScreen
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel
import com.android.myfooddiarybookaos.model.detail.DiaryDetail

@Composable
fun MainDetailScreen(
    diaryState: DiaryState,
    topDate: String,
    initMemo: () -> Unit,
    currentViewState: MutableState<DiaryViewState>,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val currentViewImageId = remember { mutableStateOf(-1) }
    val diaryDetail = detailViewModel.diaryDetail.value
    // 다이어리 업데이트
    if (diaryState.isSelectedGallery.value) {
        when (diaryState.addScreenState.value) {

            AddScreenState.FIX_IMAGE_IN_DETAIL -> {
                if (diaryState.fixImageId.value != -1) {

                    diaryState.multiPartList.firstOrNull()?.let { firstImage ->

                        detailViewModel.fixDiaryImage(
                            diaryState.fixImageId.value, firstImage,
                            addState = {
//                                if (it) detailViewModel.setDiaryDetail {  }
                            }
                        )
                        diaryState.fixImageId.value = -1
                    }
                }
            }
            AddScreenState.ADD_IMAGE_IN_DETAIL -> {
                detailViewModel.addDiaryImages(
                    diaryState.currentDiaryDetail.value,
                    diaryState.multiPartList,
                    addState = {
//                        if (it) detailViewModel.setDiaryDetail {  }
                    }
                )
            }
            else -> {}
        }
        diaryState.resetSelectedInfo()
    }

    Column {
        DetailTopLayer(
            currentViewImageId.value, topDate
        )
        ImageSliderScreen(
            diaryDetail?.images,
            currentViewImageId
        )
        Surface(
            modifier = Modifier.padding(start = 21.dp, top = 25.dp)
        ) {
            DetailMenuTime(diaryDetail = diaryDetail)
        }
        Spacer(modifier = Modifier.height(10.dp))
        DetailData(
            diaryDetail,
            fixMemo = {
                initMemo()
                currentViewState.value = DiaryViewState.MEMO
            }
        )
    }
}