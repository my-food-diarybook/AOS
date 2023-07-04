package com.example.myfooddiarybookaos.Login

import android.graphics.drawable.DrawableWrapper
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.resource.drawable.DrawableResource
import com.example.myfooddiarybookaos.Login.ui.theme.MyFoodDiaryBookAOSTheme
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
        Text(
            text = "식사일기",
            fontWeight = FontWeight(700),
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            fontSize = dimensionResource(id = R.dimen.size_22_sp).value.sp,
            color = colorResource(id = R.color.main_color)
        )
        Text(
            text = "오늘 먹은 음식을 사진으로 기록하세요!",
            fontWeight = FontWeight(400),
            fontFamily = FontFamily(Font(R.font.roboto_light)),
            fontSize = dimensionResource(id = R.dimen.size_16_sp).value.sp,
            color = colorResource(id = R.color.line_color_deep)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_55)))
    }
}

@Composable
fun MidLayout(){
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // EditText - 이메일
        TextField(
            value = text,
            onValueChange = {newText -> text = newText}, //값 변경 시
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            placeholder = { Text(
                text = "이메일",
                color = colorResource(id = R.color.weak_color),
            )}, //힌트 (텍스트 ,칼라 적용 )

        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_8)))
        // EditText - pw
        TextField(
            value = text,
            onValueChange = {newText -> text = newText}, //값 변경 시
            // End 아이콘
            trailingIcon = { painterResource(id = R.drawable.ic_eye_off_black)},
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            placeholder = { Text(
                text = "비밀번호",
                color = colorResource(id = R.color.weak_color),

                )} //힌트
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_29)))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyFoodDiaryBookAOSTheme {
        Greeting("Android")
    }
}