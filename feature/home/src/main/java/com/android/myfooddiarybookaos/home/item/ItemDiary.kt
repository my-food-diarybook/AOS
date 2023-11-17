package com.android.myfooddiarybookaos.home.item

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.model.DayDate
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap

@Composable
fun ItemDiary(
    dayDate: DayDate,
    dayClick: () -> Unit,
    imageByte: String?
) {

    val textView by animateColorAsState(
        when (dayDate.isSelected) {
            1 -> colorResource(id = R.color.line_color_deep)
            0 -> Color.White
            else -> colorResource(id = R.color.color_day_of_weak)
        }
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(
                onClick = {
                    if (dayDate.isSelected < 1) dayClick()
                })
            .aspectRatio(1f),
    ) {
        if (dayDate.isSelected == 0 && imageByte == null) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(2.29.dp)
                    .background(
                        colorResource(id = R.color.main_color),
                        CircleShape
                    )
            )
        }

        if (dayDate.day != 0 && imageByte == null) {
            Text(
                text = dayDate.day.toString(),
                fontWeight = FontWeight(400),
                fontFamily = robotoBold,
                fontSize = 12.sp,
                color = textView,
            )
        }
        if (imageByte != null) {
            Image(
                rememberAsyncImagePainter(byteStringToBitmap(imageByte)),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop,
            )
        }
    }
}