package com.android.myfooddiarybookaos.myaccount.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.utils.scaledSp


@Composable
fun Subject(text: String) {
    TextBox(
        text = text,
        fontFamily = robotoBold,
        fontSize = 14.scaledSp(),
        fontWeight = 700,
        color = colorResource(id = R.color._1A1D1D),
        lineHeight = 21.scaledSp()
    )
}
