package com.android.myfooddiarybookaos.login.mainSubUi

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.ui.theme.EditTextBox

@Composable
fun MidLayout(){
    var isValid by remember{
        mutableStateOf(true)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // EditText - email
        EditTextBox("이메일")
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_8)))
        // EditText - pw
        EditTextBox(hintText = "비밀번호")

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_4)))
        Text(
            text =  if (!isValid)"*아이디 또는 비밀번호를 잘못 입력했습니다. (n/5)"
                    else "",
            color = colorResource(id = R.color.not_valid_text_color),
            fontFamily = robotoRegular,
            fontWeight = FontWeight(500),
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_7)))

        // LoginButton
        Surface( // 배경
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.size_16),
                    end = dimensionResource(id = R.dimen.size_16),
                )
                .alpha(0.3F),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4)),
            border = BorderStroke(
                dimensionResource(id = R.dimen.size_1),
                colorResource(id = R.color.weak_color)
            ),
            color = colorResource(id = R.color.main_color)
        ) {
            Text(
                text = "로그인",
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight(700),
                fontSize = 16.sp ,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        top = dimensionResource(id = R.dimen.size_10_5),
                        bottom = dimensionResource(id = R.dimen.size_10_5)
                    ),
                textAlign = TextAlign.Center, // 중앙
            )
        }


    }
}