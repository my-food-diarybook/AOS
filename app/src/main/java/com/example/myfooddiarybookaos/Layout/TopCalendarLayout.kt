package com.example.myfooddiarybookaos.Layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import com.example.myfooddiarybookaos.R
import com.example.myfooddiarybookaos.ui.theme.TextBox


@Composable
fun TopCalendarLayout(monthString : String){
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.size_47),
                start = dimensionResource(id = R.dimen.size_20),
                bottom = dimensionResource(id = R.dimen.size_11_59)
            )
    ){
        TextBox(
            text = monthString,
            fontWeight = 700,
            fontFamily = Font(R.font.roboto_bold),
            fontSize = dimensionResource(id = R.dimen.size_34).value.sp,
            color = colorResource(id = R.color.black) )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_11_59)))
        Image(
            painter = painterResource(id = R.drawable.downarrow),
            contentDescription = "",
            Modifier.padding(bottom = dimensionResource(id = R.dimen.size_3_33))
        )
    }
    Divider(
        color = colorResource(id = R.color.line_color),
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.size_1))
            .fillMaxWidth()
    )
}