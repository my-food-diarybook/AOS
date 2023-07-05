package com.example.myfooddiarybookaos.Login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myfooddiarybookaos.Login.ui.theme.EditTextBox
import com.example.myfooddiarybookaos.Login.ui.theme.MyFoodDiaryBookAOSTheme
import com.example.myfooddiarybookaos.Login.ui.theme.TextBox
import com.example.myfooddiarybookaos.R

class LoginActivity_jet : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFoodDiaryBookAOSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TopLayout()
                        MidLayout()
                        BottomLayout()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun TopLayout(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_44)))
        Image(
            painter = painterResource(
                id = R.drawable.main_image),
            contentDescription = "main image",
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.size_111))
                .height(dimensionResource(id = R.dimen.size_83))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_13)))
        TextBox(
            "식사일기",
            700,
            Font(R.font.roboto_bold),
            dimensionResource(id = R.dimen.size_22_sp).value.sp,
            colorResource(id = R.color.main_color)
        )
        TextBox(
            "오늘 먹은 음식을 사진으로 기록하세요!",
            400,
            Font(R.font.roboto_light),
            dimensionResource(id = R.dimen.size_16_sp).value.sp,
            colorResource(id = R.color.line_color_deep)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_55)))
    }
}

@Composable
fun MidLayout(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // EditText - email
        EditTextBox("이메일")
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_8)))
        // EditText - pw
        EditTextBox(hintText = "비밀번호")
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_29)))
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
                fontSize =dimensionResource(id = R.dimen.size_16_sp).value.sp ,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        top = dimensionResource(id = R.dimen.size_12),
                        bottom = dimensionResource(id = R.dimen.size_12)
                    ),
                textAlign = TextAlign.Center, // 중앙
            )
        }


    }
}

@Composable
fun BottomLayout(){
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_17)))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextBox(
            text = "비밀번호 찾기",
            fontWeight = 500,
            fontFamily = Font(R.font.roboto_light),
            dimensionResource(id = R.dimen.size_14_sp).value.sp,
            colorResource(id = R.color.login_weak_color)
        )
        // 중앙 선 표현
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_12)))
        Divider(
            color = colorResource(id = R.color.login_weak_color),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.size_1))
                .height(dimensionResource(id = R.dimen.size_12_86))
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_12)))
        TextBox(
            text = "회원가입",
            fontWeight = 500,
            fontFamily = Font(R.font.roboto_light),
            dimensionResource(id = R.dimen.size_14_sp).value.sp,
            colorResource(id = R.color.login_weak_color)
        )
    }
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_55_71)))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyFoodDiaryBookAOSTheme {
        Greeting("Android")
    }
}