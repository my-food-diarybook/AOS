package com.android.myfooddiarybookaos.detail.locationUi.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.model.map.Place

@Composable
fun CurrentLocationItem(
    place: Place,
    onSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onSelected() },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = place.place_name,
            fontWeight = FontWeight.W500,
            fontSize = 18.scaledSp(),
            color = colorResource(id = R.color.calender_next_color),
            fontFamily = robotoRegular,
            lineHeight = 18.scaledSp()
        )
    }
}
