package com.android.myfooddiarybookaos.home.item

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.customOuterShadow
import com.android.myfooddiarybookaos.data.function.DiaryTime
import com.android.myfooddiarybookaos.model.home.HomeDay
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun ItemHomeDay(
    homeDay: HomeDay,
    clickDiary: () -> Unit,
) {
    val homeDayTags = remember {
        mutableStateOf(
            if (homeDay.tags.isNotEmpty()) "#" +
                    homeDay.tags.joinToString(" #")
            else ""
        )
    }

    val imageBitmap = remember {
        mutableStateOf(byteStringToBitmap(homeDay.image.bytes))
    }
    Surface(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .customOuterShadow(
                color = colorResource(id = R.color.black_10),
                offsetY = 1.dp,
                blurRadius = 4f
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(146.dp)
                .clickable {
                    clickDiary()
                }
        ) {
            Column {

                AsyncImage(
                    model = imageBitmap.value,
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                        .fillMaxWidth()
                        .height(96.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    onSuccess = {}
                )

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(
                            text = DiaryTime.getDiaryTimeData(homeDay.diaryTime),
                            fontFamily = FontFamily(
                                Font(
                                    R.font.roboto_regular,
                                    FontWeight.W500
                                )
                            ),
                            fontSize = 16.scaledSp(),
                            color = Color.Black,
                            lineHeight = 16.scaledSp()
                        )
                        Text(
                            text = homeDayTags.value,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.roboto_regular,
                                    FontWeight.W500
                                )
                            ),
                            fontSize = 12.scaledSp(),
                            color = colorResource(id = R.color.main_color),
                            lineHeight = 12.scaledSp()
                        )
                    }
                }

            }
        }

    }
}