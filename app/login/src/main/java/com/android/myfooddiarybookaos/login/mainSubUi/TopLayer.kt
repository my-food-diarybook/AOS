package com.android.myfooddiarybookaos.login.mainSubUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.ui.theme.TextBox
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun TopLayout(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(41.dp))
        Image(
            painter = painterResource(id = R.drawable.main_image),
            contentDescription = "main image",
            modifier = Modifier
                .width(111.dp)
                .height(83.dp),
            colorFilter = ColorFilter.tint(colorResource(id = R.color.main_color))
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextBox(
            "식사일기",
            700,
            Font(R.font.roboto_bold),
            22.scaledSp(),
            colorResource(id = R.color.main_color)
        )
        TextBox(
            "오늘 먹은 음식을 사진으로 기록하세요!",
            400,
            Font(R.font.roboto_serif_regular),
            16.scaledSp(),
            colorResource(id = R.color.line_color_deep)
        )
        Spacer(modifier = Modifier.height(55.dp))
    }
}
