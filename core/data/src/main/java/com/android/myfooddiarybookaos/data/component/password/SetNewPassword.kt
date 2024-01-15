package com.android.myfooddiarybookaos.data.component.password

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.ui.theme.EditTextBox
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.login.passUi.PasswordPolicyLayer

@Composable
fun SetNewPassword(
    navController : NavHostController,
    realPass : String
) {
    var checkEnter by remember { mutableStateOf(0.3f) }

    val currentPass = remember { mutableStateOf(TextFieldValue("")) }
    val newPass = remember { mutableStateOf(TextFieldValue("")) }
    val newPassRe = remember { mutableStateOf(TextFieldValue("")) }

    val isEqualCurrentPass = remember { mutableStateOf(false) } // real = current
    val isValidPass = remember { mutableStateOf(false) } // new pass valid
    val isSamePass = remember { mutableStateOf(false) } //pass = passRe

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
                "비밀번호 설정",
                fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.W700)),
                fontSize = 18.scaledSp(),
                color = colorResource(id = R.color._1A1D1D)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "보안을 위해 \n" +
                    "비밀번호를 변경하세요.",
            fontWeight = FontWeight.W700,
            fontFamily = robotoBold,
            fontSize = 20.scaledSp(),
            color = colorResource(id = R.color._1A1D1D)
        )
        Spacer(modifier = Modifier.height(8.dp))

        isEqualCurrentPass.value = realPass==currentPass.value.text || currentPass.value.text.isEmpty()
        Subject("현재 비밀번호")
        Spacer(modifier = Modifier.height(4.dp))
        EditTextBox("비밀번호",currentPass,isEqualCurrentPass)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if(isEqualCurrentPass.value || currentPass.value.text.isEmpty()) "" else "*비밀번호를 다시 확인해주세요.",
            fontWeight = FontWeight.W500,
            fontSize = 12.scaledSp(),
            fontFamily = robotoRegular,
            color = colorResource(id = R.color.not_valid_text_color)
        )
        Spacer(modifier = Modifier.height(6.dp))

        PasswordPolicyLayer(
            newPass,
            newPassRe,
            isSamePass,
            isValidPass,
            subjectName = "새 비밀번호"
        )

        Spacer(modifier = Modifier.height(24.dp))

        checkEnter =
            if (isValidPass.value && isSamePass.value && isEqualCurrentPass.value
                && currentPass.value.text.isNotEmpty()
                && newPass.value.text.isNotEmpty()
                && newPassRe.value.text.isNotEmpty()
            ) 1.0f
            else 0.3f
        // 변경 완료
        Surface( // 배경
            modifier = Modifier
                .alpha(checkEnter)
                .clickable {
                           // new pass !
                },
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(
                1.dp,
                colorResource(id = R.color.weak_color)
            ),
            color = colorResource(id = R.color.main_color)
        ) {
            Text(
                text = "변경 설정 완료",
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight(700),
                fontSize = 16.scaledSp() ,
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
@Preview(showBackground = true)
fun SetNewPassPreview(){
    SetNewPassword(rememberNavController(),"wlsdn1234@@")
}


@Composable
fun Subject(text : String){
    Text(
        text = text,
        fontWeight = FontWeight.W700,
        fontFamily = robotoBold,
        fontSize = 14.scaledSp(),
        color = colorResource(id = R.color._1A1D1D)
    )
}