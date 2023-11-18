package com.android.myfooddiarybookaos.detail.mainUi.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.function.DiaryTime
import com.android.myfooddiarybookaos.model.detail.DiaryDetail

@Composable
fun DetailMenuTime(
    diaryDetail: DiaryDetail?
) {
    Box(
        modifier = Modifier
            .border(
                1.dp,
                colorResource(id = R.color.main_color),
                RoundedCornerShape(4.dp)
            )
            .height(28.dp)
            .width(54.dp),
        contentAlignment = Alignment.Center
    ) {
        diaryDetail?.diaryTime?.let { timeData ->
            Text(
                color = colorResource(id = R.color.main_color),
                text = DiaryTime.getDiaryTimeData(timeData),
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W100))
            )
        }
    }
}