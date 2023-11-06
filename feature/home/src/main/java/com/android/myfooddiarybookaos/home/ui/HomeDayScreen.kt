package com.android.myfooddiarybookaos.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.coloredShadow
@Composable
fun HomeDayScreen(

) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .padding(start = 10.dp, bottom = 9.dp)
                    .align(Alignment.BottomStart),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.main_left),
                    contentDescription = null
                )
            }
            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .coloredShadow(
                        color = colorResource(id = R.color.black_10),
                        offsetY = 1.dp,
                        blurRadius = 4.dp
                    )
            )
        }
        
        Spacer(modifier = Modifier.height(6.dp))

        // 스크롤 박스 (좌우 )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp)
        ){

        }

        // item 상, 하로 8dp, 첫 item 상 (17dp - 8dp = 9dp)
        Spacer(modifier = Modifier.height(9.dp))

        //여기 아이템 리스트 추가

    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent)){
        Box(
            modifier = Modifier
                .size(64.dp)
                .padding(5.33.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.add_button),
                contentDescription = null
            )
        }
    }
}