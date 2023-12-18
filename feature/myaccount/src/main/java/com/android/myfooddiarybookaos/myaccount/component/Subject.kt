package com.android.myfooddiarybookaos.myaccount.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.robotoBold


@Composable
fun Subject(text: String) {
    TextBox(
        text = text,
        fontFamily = robotoBold,
        fontSize = 14.sp,
        fontWeight = 700,
        color = colorResource(id = R.color._1A1D1D)
    )
}