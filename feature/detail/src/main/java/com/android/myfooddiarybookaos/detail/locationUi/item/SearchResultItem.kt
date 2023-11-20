package com.android.myfooddiarybookaos.detail.locationUi.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import com.android.myfooddiarybookaos.model.map.Place
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.core.data.R

@Composable
fun SearchResultItem(
    query: String,
    place: Place,
    onSelected: () -> Unit
) {
    Column {
        Text(
            text = place.place_name,
            fontFamily = robotoRegular,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            color = Color.Black
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = place.address_name,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                fontFamily = robotoRegular,
                color = colorResource(id = R.color.line_color_deep)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Divider(
                Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(colorResource(id = R.color.line_color_deep))
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = place.distanc,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                fontFamily = robotoRegular,
                color = colorResource(id = R.color.line_color_deep)
            )
        }
    }
}