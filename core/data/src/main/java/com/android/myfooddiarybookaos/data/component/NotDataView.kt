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
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular

@Composable
fun NotDataView(){
    Column(
        modifier = Modifier
            .fillMaxSize()
        , horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextBox(
            text = "등록한 식사일기가 없습니다.",
            fontWeight = 300,
            fontFamily = robotoRegular,
            20.sp ,
            color = colorResource(id = R.color.black)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_24)))
        TextBox(
            text = "오늘 먹은 식사를 사진으로 기록하세요.",
            fontWeight = 300,
            fontFamily = robotoRegular,
            fontSize = 14.sp ,
            color = colorResource(id = R.color.black)
        )
    }
}

@Preview
@Composable
fun NotDataPreview(){
    NotDataView()
}