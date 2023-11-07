package com.android.myfooddiarybookaos.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.core.data.R
@Composable
fun HomeDayTopLayer(
    currentDate: String,
    prevDate: String,
    nextDate: String
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = prevDate,
            fontFamily = robotoRegular,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp,
            color = colorResource(id = R.color.calender_next_color),
            modifier = Modifier.align(Alignment.Center)
        )

        Text(
            text = currentDate,
            fontFamily = robotoRegular,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = nextDate,
            fontFamily = robotoRegular,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp,
            color = colorResource(id = R.color.calender_next_color),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}