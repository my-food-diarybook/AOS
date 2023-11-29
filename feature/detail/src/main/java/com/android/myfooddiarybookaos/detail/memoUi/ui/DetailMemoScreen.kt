package com.android.myfooddiarybookaos.detail.mainUi.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.detail.mainUi.component.DetailLocation
import com.android.myfooddiarybookaos.detail.memoUi.component.MemoTopLayer
import com.android.myfooddiarybookaos.detail.memoUi.component.SelectTimeLayer
import com.android.myfooddiarybookaos.detail.memoUi.component.TypeMemo
import com.android.myfooddiarybookaos.detail.memoUi.component.TypeTag
import com.android.myfooddiarybookaos.data.state.DetailFixState
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel

@Composable
fun DetailMemoScreen(
    diaryFixState: DetailFixState,
    currentViewState: MutableState<DiaryViewState>,
    detailViewModel : DetailViewModel = hiltViewModel()
) {
    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {
        currentViewState.value = DiaryViewState.MAIN
    })
    Column {
        MemoTopLayer(
            backStage = {
                currentViewState.value = DiaryViewState.MAIN
            },
            nextStage = {
                currentViewState.value = DiaryViewState.MAIN
                // + 상태 저장
                detailViewModel.setFixResult(diaryFixState)
            }
        )
        Spacer(modifier = Modifier.height(22.dp))
        SelectTimeLayer(diaryFixState.diaryTimeData)

        Column(
            modifier = Modifier.padding(start = 20.dp, end = 10.dp, top = 12.dp)
        ) {
            TypeMemo(text = diaryFixState.memo, editMemo = {diaryFixState.setMemo(it)})
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier.clickable {
                    currentViewState.value = DiaryViewState.LOCATION
                }
            ) {
                DetailLocation(diaryFixState.place.value)
            }
            Spacer(modifier = Modifier.height(15.dp))
            TypeTag(
                tags = diaryFixState.tags,
                addTag = {
                    diaryFixState.addTag(it)
                         },
                removeTag = {
                    diaryFixState.removeTag(it)
                }
            )
        }
    }
}