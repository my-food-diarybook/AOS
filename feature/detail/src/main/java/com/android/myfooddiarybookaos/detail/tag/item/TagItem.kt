package com.android.myfooddiarybookaos.detail.tag.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R

@Composable
fun TagItem(
    text: String,
    click: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clickable { click() }
    ) {
        Text(
            text = "#$text",
            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
            fontSize = 18.sp,
            color = Color.Black,
        )
    }
}