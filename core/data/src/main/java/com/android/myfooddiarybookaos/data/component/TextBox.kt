package com.android.myfooddiarybookaos.data

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextBox(
    text:String,
    fontWeight: Int,
    fontFamily: FontFamily?,
    fontSize: TextUnit,
    color: Color,
    lineHeight : TextUnit? = null
){
    if (lineHeight != null) {
        Text(
            text = text,
            fontWeight = FontWeight(fontWeight),
            fontFamily = fontFamily,
            fontSize = fontSize,
            color = color,
            lineHeight = lineHeight
        )
    }else {
        Text(
            text = text,
            fontWeight = FontWeight(fontWeight),
            fontFamily = fontFamily,
            fontSize = fontSize,
            color = color,
        )
    }
}