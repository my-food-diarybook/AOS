package com.android.myfooddiarybookaos.detail.mainUi.popUp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular

@Composable
fun DeleteDiaryPopUp(
    onDelete: () -> Unit,
    close: () -> Unit
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
                text = "삭제하시겠습니까?",
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
                            close()
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
                            close()
                            onDelete()
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