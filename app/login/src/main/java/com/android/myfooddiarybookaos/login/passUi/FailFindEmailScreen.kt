package com.android.myfooddiarybookaos.login.passUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.robotoRegular

@Composable
fun FailFindEmailScreen(){
    Surface(
        modifier = Modifier.background(
            color = Color.White,
            RoundedCornerShape(dimensionResource(id = R.dimen.size_8))
        ),
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(dimensionResource(id = R.dimen.size_24)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "이메일 확인 실패",
                fontSize = 16.sp,
                fontFamily = robotoBold,
                fontWeight = FontWeight.W700,
                color = colorResource(id = R.color.text_dark)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_28)))

            Text(
                "입력하신 이메일로 가입한 회원을 \n" +
                        "찾을 수 없습니다. \n" +
                        "확인 후 다시 입력해 주세요.",
                maxLines = 3,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontFamily = robotoRegular,
                fontWeight = FontWeight.W500,
                color = colorResource(id = R.color.text_dark_pop)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_28)))

            Row(modifier = Modifier.wrapContentSize()){
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.popup_button_white),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4))
                        )
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "다시 입력",
                        fontWeight = FontWeight.W700,
                        fontFamily = robotoBold,
                        fontSize = 17.sp,
                        color = colorResource(id = R.color._3A3A3D),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(110.dp)
                            .padding(
                                vertical = dimensionResource(id = R.dimen.size_10_5),
                                horizontal = dimensionResource(id = R.dimen.size_12)
                            )
                    )
                }

                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_8)))

                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.main_color),
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4))
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "회원가입",
                        fontWeight = FontWeight.W700,
                        fontFamily = robotoBold,
                        fontSize = 17.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(110.dp)
                            .padding(
                                vertical = dimensionResource(id = R.dimen.size_10_5),
                                horizontal = dimensionResource(id = R.dimen.size_12)
                            )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ViewEmailFail(){
    FailFindEmailScreen()
}