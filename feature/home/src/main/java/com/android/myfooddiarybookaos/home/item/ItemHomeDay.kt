package com.android.myfooddiarybookaos.home.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.home.function.diaryTimeData
import com.android.myfooddiarybookaos.model.home.HomeDay
import com.android.myfooddiarybookaos.path.byteStringToBitmap

@Composable
fun ItemHomeDay(
    homeDay: HomeDay
) {
    val homeDayTags =
        if (homeDay.tags.isNotEmpty()) "#" +
                homeDay.tags.joinToString(" #")
        else ""

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(146.dp)
            .padding(
                vertical = 8.dp,
                horizontal = 20.dp
            )
            .clip(RoundedCornerShape(4.dp))
            .coloredInnerShadow(
                color = colorResource(id = R.color.black_10),
                offsetY = 1.dp,
                blurRadius = 4.dp
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                rememberAsyncImagePainter(byteStringToBitmap(homeDay.image.bytes)),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .fillMaxWidth()
                    .height(96.dp),
                contentScale = ContentScale.Crop,
            )


            Box(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)) {
                Box(modifier = Modifier
                    .height(34.dp)
                    .fillMaxWidth()) {
                    Text(
                        text = diaryTimeData(homeDay.diaryTime),
                        fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.W700)),
                        fontSize = 12.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                    Text(
                        text = homeDayTags,
                        fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.W700)),
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.main_color),
                        modifier = Modifier.align(Alignment.BottomStart)
                    )
                }
            }

        }
    }
}