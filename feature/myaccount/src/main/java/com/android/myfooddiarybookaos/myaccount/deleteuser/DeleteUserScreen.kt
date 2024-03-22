package com.android.myfooddiarybookaos.myaccount.deleteuser

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun DeleteUserScreen(
    myNavi: NavHostController,
) {
    val checkBox = remember { mutableStateOf(false) }
    val deleteColorState = animateColorAsState(
        targetValue = if (checkBox.value) colorResource(R.color.main_color) else colorResource(R.color.main_color_30),
        label = ""
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                modifier = Modifier
                    .clickable {
                        myNavi.popBackStack()
                    }
                    .align(Alignment.BottomStart)
                    .padding(start = 9.dp,bottom = 2.dp)
                    .size(34.dp),
                painter = painterResource(R.drawable.pass_left_side),
                contentDescription = null
            )
            Box(modifier = Modifier.padding(bottom = 14.75.dp)) {
                TextBox(
                    text = "회원탈퇴",
                    fontWeight = 500,
                    fontFamily = robotoRegular,
                    fontSize = 18.scaledSp(),
                    color = colorResource(id = R.color.black),
                )
            }
        }
        Divider(
            modifier = Modifier
                .height(2.dp)
                .coloredInnerShadow(
                    color = colorResource(id = R.color.black_10),
                    offsetY = 1.dp,
                    blurRadius = 4.dp
                )
        )

        Spacer(modifier = Modifier.height(63.dp))
        TextBox(
            text = "식사일기 회원탈퇴",
            fontWeight = 600,
            fontFamily = robotoRegular,
            fontSize = 17.scaledSp(),
            color = colorResource(id = R.color.text_dark),
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "식사일기에서 작성한 모든 일기와 \n계정정보가 삭제됩니다.\n" +
                    " \n" +
                    "삭제된 정보는 다시 복구 할 수 없습니다. ",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            fontWeight = FontWeight.W600,
            fontFamily = robotoRegular,
            fontSize = 18.scaledSp(),
            color = colorResource(id = R.color.text_dark_pop),
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 23.dp)
        ) {
            CheckBox(checkBox, "전부 삭제하고 탈퇴하는 것에 동의합니다.", FontWeight.W700, null)
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {

            Surface(
                color = colorResource(id = R.color.popup_button_white),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        myNavi.popBackStack()
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

            Spacer(modifier = Modifier.width(12.58.dp))

            Surface(
                color = deleteColorState.value,
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        if(checkBox.value){

                        }
                    }
            ) {
                Text(
                    text = "회원탈퇴",
                    fontFamily = robotoRegular,
                    fontWeight = FontWeight.W700,
                    fontSize = 15.scaledSp(),
                    lineHeight = 23.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.5.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
        Spacer(modifier = Modifier.height(72.dp))
    }
}

@Composable
@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true
)
private fun DeleteUserScreenPreview(){
    DeleteUserScreen(rememberNavController())
}

@Composable
fun CheckBox(
    checkState: MutableState<Boolean>,
    text: String,
    fontWeight: FontWeight,
    link: String?
) {
    val context = LocalContext.current
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .padding(3.dp)
                .clickable {
                    checkState.value = !checkState.value
                }, contentAlignment = Alignment.Center
        ) {
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
            fontSize = 14.scaledSp(),
            color = colorResource(id = R.color._1A1D1D),
            fontWeight = fontWeight,
            textDecoration = if (fontWeight != FontWeight.W700) TextDecoration.Underline else null,
            modifier = Modifier.clickable {
                if (link != null) {
                    val intent = Intent(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(link)
                        )
                    )
                    context.startActivity(intent)
                }
            }
        )
    }
}
