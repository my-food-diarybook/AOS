package com.android.myfooddiarybookaos.home.item

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.model.DayDate

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
                fontFamily = robotoRegular,
                fontSize = 12.scaledSp(),
                color = textView,
            )
        }
        if (imageByte != null) {
            val imageState = remember { byteStringToBitmap(imageByte) }
            AsyncImage(
                model = imageState,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop,
                onSuccess = {}
            )
        }
    }
}
