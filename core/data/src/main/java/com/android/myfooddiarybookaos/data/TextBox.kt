package com.android.myfooddiarybookaos.data

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextBox(
    text:String,
    fontWeight: Int,
    fontFamily: Font,
    fontSize: TextUnit,
    color : Color,
){
    Text(
        text = text,
        fontWeight = FontWeight(fontWeight),
        fontFamily = FontFamily(fontFamily),
        fontSize = fontSize,
        color = color
    )
}