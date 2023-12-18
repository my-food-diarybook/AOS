package com.android.myfooddiarybookaos.myaccount.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R

@Composable
fun EmailInfo(email: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = 7.dp,
            end = 9.17.dp,
            top = 17.dp,
            bottom = 17.dp
        )
    ) {
        Box(modifier = Modifier
            .width(270.dp)
            .align(Alignment.CenterStart)
        ) {
            Text(
                text = email,
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                color = colorResource(id = R.color.black),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Box(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterEnd)
            , contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.right_side_my),
                contentDescription = "",
            )
        }
    }
}