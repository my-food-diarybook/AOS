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
import com.android.myfooddiarybookaos.model.detail.DiaryDetail

@Composable
fun DetailMemo(
    memo : String?

) {
    if (memo == null || memo == "") {
        Text(
            text = "메모 남기기",
            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
            fontSize = 18.sp,
            color = colorResource(id = R.color.calender_next_color)
        )
    } else {
        Text(
            text = memo,
            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
            fontSize = 18.sp,
            color = Color.Black,
        )
    }
}