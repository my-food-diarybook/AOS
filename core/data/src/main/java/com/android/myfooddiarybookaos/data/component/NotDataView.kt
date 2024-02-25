package com.android.myfooddiarybookaos.Layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun NotDataView() {
    Column(
        modifier = Modifier
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextBox(
            text = "등록한 식사일기가 없습니다.",
            fontWeight = 300,
            fontFamily = robotoRegular,
            20.scaledSp(),
            color = colorResource(id = R.color.black),
            lineHeight = 20.scaledSp()
        )
        TextBox(
            text = "오늘 먹은 식사를 사진으로 기록하세요.",
            fontWeight = 300,
            fontFamily = robotoRegular,
            fontSize = 14.scaledSp(),
            color = colorResource(id = R.color.black),
            lineHeight = 20.scaledSp()
        )
        Spacer(modifier = Modifier.height(41.dp))
    }
}

@Preview
@Composable
fun NotDataPreview() {
    NotDataView()
}
