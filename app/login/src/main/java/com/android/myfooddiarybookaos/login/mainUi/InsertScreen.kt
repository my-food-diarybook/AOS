package com.android.myfooddiarybookaos.login.mainUi

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.ui.theme.EditTextBox
import com.android.myfooddiarybookaos.login.passUi.PasswordPolicyLayer
import com.android.myfooddiarybookaos.login.passUi.Subject

@SuppressLint("UnrememberedMutableState")
@Composable
fun InsertScreen(
    navController : NavHostController,
){
    val emailText = remember{ mutableStateOf(TextFieldValue("")) }
    val passText = remember{ mutableStateOf(TextFieldValue("")) }
    val rePassText = remember { mutableStateOf(TextFieldValue("")) }
    val isValidPass = remember { mutableStateOf(false) }
    val isSamePass = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.size_16))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_40)))

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
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_4)))
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
    }
}


@Composable
@Preview
fun InsertPreview(){
    InsertScreen(rememberNavController())
}