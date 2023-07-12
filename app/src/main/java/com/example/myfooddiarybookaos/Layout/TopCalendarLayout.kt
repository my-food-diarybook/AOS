package com.example.myfooddiarybookaos.Layout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role.Companion.Image
import com.example.myfooddiarybookaos.R


@Composable
fun TopCalendarLayout(){
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
        Text(

        )
        Image(
            
        )
    }
    Divider(
        color = colorResource(id = R.color.line_color),
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.size_1))
            .fillMaxWidth()
    )
}