package com.android.myfooddiarybookaos.login.mainSubUi

import androidx.compose.foundation.Image
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
fun BottomLayout(){
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_13)))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextBox(
            text = "비밀번호 찾기",
            fontWeight = 500,
            fontFamily = Font(R.font.roboto_light),
            14.sp,
            colorResource(id = R.color.login_weak_color)
        )
        // 중앙 선 표현
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_12)))
        Divider(
            color = colorResource(id = R.color.login_weak_color_40),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.size_1))
                .height(dimensionResource(id = R.dimen.size_12_86))
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_12)))
        TextBox(
            text = "회원가입",
            fontWeight = 500,
            fontFamily = Font(R.font.roboto_light),
            14.sp,
            colorResource(id = R.color.login_weak_color)
        )
    }
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_35)))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.size_16))) {
        Divider(
            color = colorResource(id = R.color.login_weak_color_40),
            modifier = Modifier
                .weight(1F)
                .width(0.dp)
                .height(dimensionResource(id = R.dimen.size_1))
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_4)))
        TextBox(
            text ="또는" ,
            fontWeight =500 ,
            fontFamily = Font(R.font.roboto_light),
            fontSize = 14.sp,
            color = colorResource(id = R.color.login_weak_color),
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_4)))
        Divider(
            color = colorResource(id = R.color.login_weak_color_40),
            modifier = Modifier
                .weight(1F)
                .width(0.dp)
                .height(dimensionResource(id = R.dimen.size_1))
        )
    }
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_3)))

    Row(verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = painterResource(id = R.drawable.icon_google),
            contentDescription = "",
            modifier = Modifier.size(dimensionResource(id = R.dimen.size_40))
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_21)))
        Image(
            painter = painterResource(id = R.drawable.icon_kakao),
            contentDescription = "",
            modifier = Modifier.size(dimensionResource(id = R.dimen.size_40))
        )
    }

}
