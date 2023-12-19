package com.android.myfooddiarybookaos.myaccount.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun CategoryMenu(text: String, count: Int) {
    Surface(
        modifier = Modifier.padding(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            TextBox(
                text = text,
                fontWeight = 500,
                fontFamily = robotoRegular,
                fontSize = 16.scaledSp(),
                color = colorResource(id = R.color.black),
                lineHeight = 24.scaledSp()
            )
            TextBox(
                text = count.toString(),
                fontWeight = 500,
                fontFamily = robotoRegular,
                fontSize = 28.scaledSp(),
                color = colorResource(id = R.color.line_color_deep),
                lineHeight = 28.scaledSp()
            )
        }
    }
}
