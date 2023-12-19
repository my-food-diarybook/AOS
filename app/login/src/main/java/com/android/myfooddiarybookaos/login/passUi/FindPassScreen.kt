package com.android.myfooddiarybookaos.login.passUi

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.AlertDialog
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.window.Dialog
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.login.navi.LoginScreenRoot
import org.junit.internal.runners.statements.Fail

@Composable
fun FindPassScreen(navController: NavHostController) {

    var emailText by remember {
        mutableStateOf("")
    }

    var emailFailState by remember {
        mutableStateOf(false)
    }

    if (emailFailState) {
        Dialog(onDismissRequest = { emailFailState = false }) {
            FailFindEmailScreen(
                offDialog = {
                    emailFailState = false
                },
                goInsert = {
                    navController.navigate(LoginScreenRoot.INSERT)
                },
            )
        }
    }

    var checkEmailValid by remember {
        mutableStateOf(0.3f)
    }

    checkEmailValid =
        if (emailText.isEmpty()) {
            0.3f
        } else {
            1.0f
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(24.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pass_left_side),
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "비밀번호 찾기",
                fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.W700)),
                fontSize = 18.scaledSp(),
                color = colorResource(id = R.color._1A1D1D)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "가입시 등록한 이메일 주소를",
            fontFamily = FontFamily(
                Font(R.font.roboto_bold, FontWeight.W700)
            ),
            fontSize = 20.scaledSp(),
            color = colorResource(id = R.color._1A1D1D)
        )
        Text(
            "입력하고 임시 비밀번호를 누르세요.",
            fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.W700)),
            fontSize = 20.scaledSp(),
            color = colorResource(id = R.color._1A1D1D),
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "이메일",
            fontSize = 14.scaledSp(),
            fontFamily = robotoRegular,
            fontWeight = FontWeight.W700,
            lineHeight = 21.scaledSp()
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Surface(
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f)
                    .wrapContentHeight(), shape = RoundedCornerShape(4.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = colorResource(id = R.color.weak_color)
                )
            ) {
                BasicTextField(
                    value = emailText,
                    onValueChange = { emailText = it },
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.text_dark),
                        fontSize = 14.scaledSp(),
                        fontFamily = robotoRegular,
                        fontWeight = FontWeight(500)
                    ),
                    modifier = Modifier
                        .padding(
                            horizontal = 12.dp,
                            vertical = 11.5.dp
                        )
                        .wrapContentHeight(),
                    cursorBrush = SolidColor(colorResource(id = R.color.main_color)),
                    maxLines = 1,
                    decorationBox = { innerTextField ->
                        if (emailText.isEmpty()) {
                            Text(
                                text = "이메일",
                                color = colorResource(id = R.color.hint_color_email),
                                fontSize = 14.scaledSp(),
                                fontFamily = robotoRegular,
                                fontWeight = FontWeight(500)
                            )
                        }
                        innerTextField()
                    }
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Surface(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        emailFailState = true
                    }
                    .alpha(checkEmailValid), shape = RoundedCornerShape(4.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = colorResource(id = R.color.main_color)
                ),
                color = colorResource(id = R.color.main_color)
            ) {
                Text(
                    text = "임시 비밀번호",
                    fontSize = 15.scaledSp(),
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 11.5.dp
                    ),
                    fontWeight = FontWeight(700),
                    fontFamily = robotoRegular,
                    color = colorResource(id = R.color.white),
                )
            }
        }

    }
}
