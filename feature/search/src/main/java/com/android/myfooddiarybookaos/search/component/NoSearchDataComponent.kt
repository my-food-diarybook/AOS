package com.android.myfooddiarybookaos.search.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.data.robotoRegular

@Composable
fun NoSearchDataComponent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
       Column(
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Text(
               text = "검색결과가 없습니다.",
               fontFamily = robotoRegular,
               fontWeight = FontWeight.W300,
               fontSize = 20.sp,
               lineHeight = 20.sp,
               color = Color.Black
           )
           Text(
               text = "등록한 사진을 선택해서 \n" +
                       "#태그를 입력하시면 검색하실 수 있어요. ",
               fontFamily = robotoRegular,
               fontWeight = FontWeight.W300,
               fontSize = 14.sp,
               lineHeight = 20.sp,
               color = Color.Black
           )
       }
    }
}