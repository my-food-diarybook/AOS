package com.android.myfooddiarybookaos.detail.mainUi.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.model.detail.DiaryDetail

@Composable
fun DetailLocation(
    place : String?
) {
    if (place == null || place == "") {
        Text(
            text = "위치 추가",
            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
            fontSize = 18.scaledSp(),
            color = colorResource(id = R.color.calender_next_color),
            lineHeight = 18.scaledSp()
        )
    } else {
        Text(
            text = place,
            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
            fontSize = 18.scaledSp(),
            color = Color.Black,
            lineHeight = 18.scaledSp()
        )
    }
}