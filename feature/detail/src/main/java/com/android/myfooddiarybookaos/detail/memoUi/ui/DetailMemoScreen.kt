package com.android.myfooddiarybookaos.detail.mainUi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.detail.memoUi.component.MemoTopLayer
import com.android.myfooddiarybookaos.detail.memoUi.component.SelectTimeLayer
import com.android.myfooddiarybookaos.detail.state.DetailFixState

@Composable
fun DetailMemoScreen(
    diaryFixState: DetailFixState,
    currentViewState: MutableState<DiaryViewState>
) {
    Column {
        MemoTopLayer(
            backStage = {
                currentViewState.value = DiaryViewState.MAIN
            },
            nextStage = {
                currentViewState.value = DiaryViewState.MAIN
                // + 상태 저장
            }
        )
        Spacer(modifier = Modifier.height(22.dp))
        SelectTimeLayer(diaryFixState.diaryTimeData)

    }
}