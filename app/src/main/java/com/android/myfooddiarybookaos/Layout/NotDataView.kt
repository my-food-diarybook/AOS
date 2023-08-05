package com.android.myfooddiarybookaos.Layout

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.ui.theme.TextBox

@Composable
fun NotDataView(){
    Column(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.size_314))
            .height(dimensionResource(id = R.dimen.size_63)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextBox(
            text = "등록한 식사일기가 없습니다.",
            fontWeight = 300,
            fontFamily = Font(R.font.roboto_regular) ,
            fontSize = dimensionResource(id = R.dimen.size_20).value.sp ,
            color = colorResource(id = R.color.black)
        )
        Spacer(modifier = Modifier.weight(1f))
        TextBox(
            text = "오늘 먹은 식사를 사진으로 기록하세요.",
            fontWeight = 300,
            fontFamily = Font(R.font.roboto_regular) ,
            fontSize = dimensionResource(id = R.dimen.size_14).value.sp ,
            color = colorResource(id = R.color.black)
        )
    }
}

@Preview
@Composable
fun NotDataPreview(){
    NotDataView()
}