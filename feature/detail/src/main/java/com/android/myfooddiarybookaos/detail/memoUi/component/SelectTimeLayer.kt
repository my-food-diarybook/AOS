package com.android.myfooddiarybookaos.detail.memoUi.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.data.function.DiaryTime
import com.android.myfooddiarybookaos.detail.memoUi.item.DiaryTimeData

@Composable
fun SelectTimeLayer(
    currentSelect: MutableState<String>,
) {
    LazyRow(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        state = rememberLazyListState(),
    ) {
        items(DiaryTime.getDiaryTimeData()) { diaryTime ->
            DiaryTimeData(
                diaryTime = diaryTime,
                click = {
                    currentSelect.value = diaryTime.name
                },
                currentSelect = currentSelect
            )
        }
    }
}