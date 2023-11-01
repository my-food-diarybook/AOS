package com.android.myfooddiarybookaos.login.mainUi

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.ui.theme.EditTextBox
import com.android.myfooddiarybookaos.login.passUi.PasswordPolicyLayer
import com.android.myfooddiarybookaos.login.passUi.Subject
import com.android.myfooddiarybookaos.login.viewModel.LoginViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun InsertScreen(
    navController : NavHostController,
    viewModel : LoginViewModel = hiltViewModel()
){
    val emailText = remember{ mutableStateOf(TextFieldValue("")) }
    val passText = remember{ mutableStateOf(TextFieldValue("")) }
    val rePassText = remember { mutableStateOf(TextFieldValue("")) }
    val isValidPass = remember { mutableStateOf(false) }
    val isSamePass = remember { mutableStateOf(false) }
    val allCheckBox = remember { mutableStateOf(false) }
    val serviceCheckBox = remember { mutableStateOf(false) }
    val userInfoCheckBox = remember { mutableStateOf(false) }

    var goMainResult by remember {
        mutableStateOf(false)
    }
    if (goMainResult) viewModel.goMain(LocalContext.current.applicationContext)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.pass_left_side),
                contentDescription = null,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "회원가입",
                fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.W700)),
                fontSize = 18.sp,
                color = colorResource(id = R.color._1A1D1D)
            )
        }

        Spacer(modifier = Modifier.height(13.dp))
        Subject(text = "아이디")
        Spacer(modifier = Modifier.height(4.dp))
        EditTextBox(
            hintText = "이메일",
            editText = emailText,
            strokeColor = mutableStateOf(true)
        )
        
        Spacer(modifier = Modifier.height(28.dp))
        PasswordPolicyLayer(
            newPass = passText,
            newPassRe = rePassText,
            isSamePass = isSamePass,
            isValidPass = isValidPass,
            subjectName = "비밀번호"
        )
        
        Spacer(modifier = Modifier.height(30.dp))
        CheckBox(allCheckBox,"전체 약관동의",FontWeight.W700)
        serviceCheckBox.value = allCheckBox.value
        userInfoCheckBox.value = allCheckBox.value

        CheckBox(serviceCheckBox,"(필수) 서비스 이용약관 동의",FontWeight.W400)
        CheckBox(userInfoCheckBox,"(필수) 개인정보 수집/이용 동의 ",FontWeight.W400)

        val boxColor =
            if (emailText.value.text.isNotEmpty()
                &&serviceCheckBox.value
                &&userInfoCheckBox.value
                &&isSamePass.value
                &&isValidPass.value
            ) 1.0f
            else 0.3f

        Spacer(modifier = Modifier.height(8.dp))

        Surface( // 배경
            modifier = if (boxColor == 1.0f) {
                Modifier
                    .clickable {
                        viewModel.createUser(
                            emailText.value.text,
                            passText.value.text,
                            userState = {
                                goMainResult = it
                            }
                        )
                    }
                    .alpha(boxColor)
            } else Modifier.alpha(boxColor),

            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(
                dimensionResource(id = R.dimen.size_1),
                colorResource(id = R.color.weak_color)
            ),
            color = colorResource(id = R.color.main_color)
        ) {
            Text(
                text = "가입하기",
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight(700),
                fontSize = 16.sp ,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        top = 10.5.dp,
                        bottom = 10.5.dp
                    ),
                textAlign = TextAlign.Center, // 중앙
            )
        }
    }
}


@Composable
@Preview
fun InsertPreview(){
    InsertScreen(rememberNavController())
}

@Composable
fun CheckBox(
    checkState : MutableState<Boolean>,
    text : String,
    fontWeight: FontWeight
){
    Row(verticalAlignment = Alignment.CenterVertically){
        Box(
            modifier = Modifier
                .padding(3.dp)
                .clickable {
                    checkState.value = !checkState.value
                }
            , contentAlignment = Alignment.Center
        ){
            Image(
                painter =
                if (checkState.value) painterResource(id = R.drawable.check_box_login)
                else painterResource(id = R.drawable.un_check_box_login),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontFamily = robotoRegular,
            fontSize = 14.sp,
            color = colorResource(id = R.color._1A1D1D),
            fontWeight = fontWeight
        )
    }
}