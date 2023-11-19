package com.android.myfooddiarybookaos.detail.mainUi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.detail.mainUi.component.DetailData
import com.android.myfooddiarybookaos.detail.mainUi.component.DetailMenuTime
import com.android.myfooddiarybookaos.detail.mainUi.component.DetailTopLayer
import com.android.myfooddiarybookaos.detail.mainUi.imageSlider.ImageSliderScreen
import com.android.myfooddiarybookaos.model.detail.DiaryDetail

@Composable
fun MainDetailScreen(
    topDate : String,
    diaryDetail: DiaryDetail?,
    currentViewState: MutableState<DiaryViewState>
) {
    Column {
        DetailTopLayer(topDate)
        ImageSliderScreen(diaryDetail?.images)
        Surface(
            modifier = Modifier.padding(start = 21.dp, top = 25.dp)
        ) {
            DetailMenuTime(diaryDetail = diaryDetail)
        }
        Spacer(modifier = Modifier.height(10.dp))
        DetailData(
            diaryDetail,
            fixMemo = {
                currentViewState.value = DiaryViewState.MEMO
            }
        )
    }
}