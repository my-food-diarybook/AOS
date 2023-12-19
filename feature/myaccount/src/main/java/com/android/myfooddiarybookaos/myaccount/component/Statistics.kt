package com.android.myfooddiarybookaos.myaccount.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun Statistics() {
    Box(
        modifier = Modifier
            .padding(
                top = 3.dp,
                bottom = 11.dp
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.line_color_deep),
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(
                start = 9.dp,
                end = 11.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(19.dp))
            TextBox(
                text = "모든 식사 일기",
                fontWeight = 400,
                fontFamily = robotoRegular,
                fontSize = 16.scaledSp(),
                color = colorResource(id = R.color.black),
                lineHeight = 16.scaledSp(),
            )
            TextBox(
                text = "10",
                fontWeight = 700,
                fontFamily = robotoBold,
                fontSize = 28.scaledSp(),
                color = colorResource(id = R.color.main_color),
                lineHeight = 28.scaledSp(),
            )
            Spacer(modifier = Modifier.height(12.dp))
            InDivider()
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (category in listOf("아침", "아점", "점심", "점저")) {
                    CategoryMenu(category, 0)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            InDivider()
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (category in listOf("간식", "저녁", "야식", "기타")) {
                    CategoryMenu(category, 0)
                }
            }
            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}

@Composable
private fun InDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = colorResource(id = R.color.line_color_deep)
    )
}
