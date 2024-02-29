package com.android.myfooddiarybookaos.login.passUi

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun FailFindEmailScreen(
    offDialog: () -> Unit,
    goInsert: () -> Unit
) {
    Surface(
        modifier = Modifier.background(
            color = Color.White,
            RoundedCornerShape(8.dp)
        ),
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "이메일 확인 실패",
                fontSize = 16.scaledSp(),
                fontFamily = robotoBold,
                fontWeight = FontWeight.W700,
                color = colorResource(id = R.color.text_dark),
                lineHeight = 27.scaledSp()
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                "입력하신 이메일로 가입한 회원을 \n" +
                        "찾을 수 없습니다. \n" +
                        "확인 후 다시 입력해 주세요.",
                maxLines = 3,
                fontSize = 18.scaledSp(),
                textAlign = TextAlign.Center,
                fontFamily = robotoRegular,
                fontWeight = FontWeight.W500,
                color = colorResource(id = R.color.text_dark_pop)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Row(modifier = Modifier.wrapContentSize()) {
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.popup_button_white),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .weight(1f)
                        .clickable {
                            offDialog()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "다시 입력",
                        fontWeight = FontWeight.W700,
                        fontFamily = robotoBold,
                        fontSize = 17.scaledSp(),
                        color = colorResource(id = R.color._3A3A3D),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(
                                vertical = 10.5.dp,
                                horizontal = 12.dp
                            ),
                        lineHeight = 23.scaledSp()
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.main_color),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .weight(1f)
                        .clickable {
                            offDialog()
                            goInsert()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "회원가입",
                        fontWeight = FontWeight.W700,
                        fontFamily = robotoBold,
                        fontSize = 17.scaledSp(),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(
                                vertical = 10.5.dp,
                                horizontal = 12.dp
                            ),
                        lineHeight = 23.scaledSp()
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ViewEmailFail() {
    FailFindEmailScreen(offDialog = {}, goInsert = {})
}
