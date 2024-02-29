package com.android.myfooddiarybookaos.myaccount.popUp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.ui.theme.EditTextBox

@Composable
fun CheckUserPasswordLayer(
    onClose: () -> Unit,
    onState: () -> Unit
) {
    val pwText = remember { mutableStateOf(TextFieldValue("")) }
    val isValid = remember { mutableStateOf(true) }

    val bottomNextColor = animateColorAsState(
        targetValue = if (isValid.value) {
            colorResource(id = R.color.main_color)
        } else {
            colorResource(id = R.color.main_color_30)
        }
    )

    Surface(
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "비밀번호 입력",
                fontWeight = FontWeight.W700,
                fontFamily = robotoRegular,
                fontSize = 17.sp,
                lineHeight = 27.sp,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.text_dark)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "본인 확인을 위해 비밀번호를 입력해\n주세요.",
                fontFamily = robotoRegular,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
                lineHeight = 22.sp,
                color = colorResource(id = R.color.text_dark_pop),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            EditTextBox(
                hintText = "비밀번호",
                editText = pwText,
                strokeColor = isValid,
            )

            Spacer(modifier = Modifier.height(28.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Surface(
                    color = colorResource(id = R.color.popup_button_white),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onClose()
                        }
                ) {
                    Text(
                        text = "취소",
                        fontFamily = robotoRegular,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        color = colorResource(id = R.color._3A3A3D),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.5.dp),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Surface(
                    color = bottomNextColor.value,
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onClose()
                            onState()
                        }
                ) {
                    Text(
                        text = "확인",
                        fontFamily = robotoRegular,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.5.dp),
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewCheckUserPasswordLayer() {
    CheckUserPasswordLayer(
        onClose = {}, onState = {}
    )
}
