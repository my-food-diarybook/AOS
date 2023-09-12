package com.android.myfooddiarybookaos.login.findPassUi

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
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
@Composable
fun FindPassScreen(navController : NavHostController){

    var emailText by remember {
        mutableStateOf("")
    }


    var checkEmailValid by remember {
        mutableStateOf(0.3f)
    }

    checkEmailValid =
        if (emailText.isEmpty()){ 0.3f }
        else{ 1.0f }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.size_16))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_40)))

        Row(
           verticalAlignment = Alignment.CenterVertically 
        ){
            Image(
                painter = painterResource(id = R.drawable.pass_left_side), contentDescription = null,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_4)))
            Text(
                "비밀번호 찾기",
                fontFamily =  FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight(700),
                fontSize = 18.sp,
                color = colorResource(id = R.color._1A1D1D)
            )
        }
        
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_16)))

        Image(painter = painterResource(id = R.drawable.popup_title), contentDescription = null)

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_36)))

        Text(
            text = "이메일",
            fontSize = 14.sp,
            fontFamily = robotoRegular,
            fontWeight = FontWeight(700)
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_4)))
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ){
            Surface(
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f)
                    .wrapContentHeight()
                ,shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4)),
                border = BorderStroke(
                    dimensionResource(id = R.dimen.size_1),
                    colorResource(id = R.color.weak_color)
                )
            ) {
                BasicTextField(
                    value = emailText,
                    onValueChange = { emailText = it },
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.text_dark),
                        fontSize = 14.sp,
                        fontFamily = robotoRegular,
                        fontWeight = FontWeight(500)
                    ),
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.size_12),
                            vertical = dimensionResource(id = R.dimen.size_11_5)
                        )
                        .wrapContentHeight(),
                    cursorBrush = SolidColor(colorResource(id = R.color.main_color)),
                    maxLines = 1,
                    decorationBox = {innerTextField->
                        if (emailText.isEmpty()) {
                            Text(
                                text = "이메일",
                                color = colorResource(id = R.color.hint_color_email),
                                fontSize = 14.sp,
                                fontFamily = robotoRegular,
                                fontWeight = FontWeight(500)
                            )
                        }
                        innerTextField()
                    }
                )
            }

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_8)))

            Surface(
                modifier = Modifier
                    .wrapContentSize()
                    .alpha(checkEmailValid)
                ,shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4)),
                border = BorderStroke(
                    dimensionResource(id = R.dimen.size_1),
                    colorResource(id = R.color.main_color)
                ),
                color = colorResource(id = R.color.main_color)
            ) {
                Text(
                    text = "임시 비밀번호",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.size_12),
                        vertical = dimensionResource(id = R.dimen.size_11_5)
                    ),
                    fontWeight = FontWeight(700),
                    fontFamily = robotoRegular,
                    color = colorResource(id = R.color.white),
                )
            }
        }

    }
}
