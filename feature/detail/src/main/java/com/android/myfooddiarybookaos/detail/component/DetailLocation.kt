package com.android.myfooddiarybookaos.detail.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.model.detail.DiaryDetail

@Composable
fun DetailLocation(
    diaryDetail : DiaryDetail?
) {
    if (diaryDetail?.place == null || diaryDetail.place == "") {
        Text(
            text = "위치 추가",
            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
            fontSize = 18.sp,
            color = colorResource(id = R.color.calender_next_color),
        )
    } else {

    }
}