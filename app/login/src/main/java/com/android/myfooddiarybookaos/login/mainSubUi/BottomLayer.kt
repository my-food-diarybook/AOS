package com.android.myfooddiarybookaos.login.mainSubUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.ui.theme.TextBox


@Composable
fun BottomLayout(
    findPassword : () -> Unit,
    insertUser : () -> Unit
){
    Spacer(modifier = Modifier.height(17.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier.clickable { findPassword() }
        ) {
            TextBox(
                text = "비밀번호 찾기",
                fontWeight = 500,
                fontFamily = Font(R.font.roboto_light),
                14.sp,
                colorResource(id = R.color.login_weak_color)
            )
        }
        // 중앙 선 표현
        Spacer(modifier = Modifier.width(12.dp))
        Divider(
            color = colorResource(id = R.color.login_weak_color_40),
            modifier = Modifier
                .width(1.dp)
                .height(12.86.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            Modifier.clickable { insertUser() }
        ){
            TextBox(
                text = "회원가입",
                fontWeight = 500,
                fontFamily = Font(R.font.roboto_light),
                14.sp,
                colorResource(id = R.color.login_weak_color)
            )
        }
    }
    Spacer(modifier = Modifier.height(51.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            start = 13.dp,
            end =  16.dp
        )
    ) {
        Divider(
            color = colorResource(id = R.color.login_weak_color_40),
            modifier = Modifier
                .weight(1F)
                .width(0.dp)
                .height(1.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        TextBox(
            text ="또는" ,
            fontWeight =500 ,
            fontFamily = Font(R.font.roboto_light),
            fontSize = 14.sp,
            color = colorResource(id = R.color.login_weak_color),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Divider(
            color = colorResource(id = R.color.login_weak_color_40),
            modifier = Modifier
                .weight(1F)
                .width(0.dp)
                .height(1.dp)
        )
    }
    Spacer(modifier = Modifier.height(19.dp))

    Row(verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = painterResource(id = R.drawable.icon_google),
            contentDescription = "",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(21.dp))
        Image(
            painter = painterResource(id = R.drawable.icon_kakao),
            contentDescription = "",
            modifier = Modifier.size(40.dp)
        )
    }

}
