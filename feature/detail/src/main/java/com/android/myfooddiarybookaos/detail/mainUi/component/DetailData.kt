package com.android.myfooddiarybookaos.detail.mainUi.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.model.detail.DiaryDetail

@Composable
fun DetailData(
    diaryDetail: DiaryDetail?,
    fixMemo: () -> Unit,
    fixLocation: () -> Unit,
    fixTag: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 19.dp, end = 11.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    fixMemo()
                }
        ) {
            DetailMemo(diaryDetail?.memo)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    fixLocation()
                }
        ){
            DetailLocation(diaryDetail?.place)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    fixTag()
                }
        ) {
            DetailTag(diaryDetail?.tags)
        }
    }
}
