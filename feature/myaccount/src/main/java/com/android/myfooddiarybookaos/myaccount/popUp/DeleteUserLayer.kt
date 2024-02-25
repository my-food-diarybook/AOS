package com.android.myfooddiarybookaos.myaccount.popUp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular

@Composable
fun DeleteUserLayer(
    onPassword: () -> Unit,
    onClose: () -> Unit
) {


    Surface(
        color = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(24.dp)
        ) {
            Text(
                text = "회원탈퇴 하시겠습니까?\n" +
                        "모든 식사일기와 \n" +
                        "계정정보가 삭제됩니다. ",
                fontFamily = robotoRegular,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
                lineHeight = 22.sp,
                color = colorResource(id = R.color.text_dark_pop),
                textAlign = TextAlign.Center
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
                        text = "아니오",
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
                    color = colorResource(id = R.color.main_color),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onClose()
                            onPassword()
                        }
                ) {
                    Text(
                        text = "네",
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
fun PreviewDeleteUser() {
    DeleteUserLayer(
        onClose = {},
        onPassword = {}
    )
}
