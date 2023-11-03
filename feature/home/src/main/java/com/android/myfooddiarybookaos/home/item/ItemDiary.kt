package com.android.myfooddiarybookaos.home.item

import android.util.Log
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
import androidx.compose.ui.semantics.Role.Companion.Image
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.android.myfooddiarybookaos.path.byteStringToBitmap

@Composable
fun ItemDiary(
    dayDate: DayDate,
    dayClick : (Int) -> Unit,
    imageByte : String?
) {
    val isSelected = dayDate.isSelected == 0
    val textView by animateColorAsState(
        if (dayDate.isSelected == 1) colorResource(id = R.color.line_color_deep)
        else colorResource(id = R.color.color_day_of_weak)
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(
                onClick = {
                    dayClick(dayDate.day)
                })
            .aspectRatio(1f),
    ) {
        if (isSelected && imageByte==null){
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        colorResource(id = R.color.main_color),
                        CircleShape
                    )
            )
        }

        if (dayDate.day != 0 && imageByte==null) {
            Text(
                text = dayDate.day.toString(),
                fontWeight = FontWeight(400),
                fontFamily = robotoBold,
                fontSize = 12.sp,
                color = textView,
            )
        }
        if (imageByte!=null){
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