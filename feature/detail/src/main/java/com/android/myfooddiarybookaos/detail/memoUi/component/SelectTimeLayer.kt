package com.android.myfooddiarybookaos.detail.memoUi.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.data.function.DiaryTime
import com.android.myfooddiarybookaos.detail.memoUi.item.DiaryTimeData

@Composable
fun SelectTimeLayer(
    select: (DiaryTime) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 20.dp),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ){
        items(DiaryTime.getDiaryTimeData()){ diaryTime ->
            DiaryTimeData(
                diaryTime = diaryTime,
                click = {
                    select(diaryTime)
                }
            )
        }
    }
}