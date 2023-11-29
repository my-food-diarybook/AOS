package com.android.myfooddiarybookaos.home.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.customOuterShadow
import com.android.myfooddiarybookaos.data.function.DiaryTime
import com.android.myfooddiarybookaos.model.home.HomeDay
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap

@Composable
fun ItemHomeDay(
    homeDay: HomeDay,
    clickDiary: ()-> Unit
) {

    // 여기 태그 수정 필요
    val homeDayTags =
        if (homeDay.tags.isNotEmpty()) "#" +
                homeDay.tags.joinToString(" #")
        else ""
    val imageBitmap = byteStringToBitmap(homeDay.image.bytes)

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
                    model = imageBitmap,
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                        .fillMaxWidth()
                        .height(96.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    onSuccess = {}
                )


                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(
                            text = DiaryTime.getDiaryTimeData(homeDay.diaryTime),
                            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
                            fontSize = 16.sp,
                            color = Color.Black,
                        )
                        Text(
                            text = homeDayTags,
                            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.main_color),
                        )
                    }
                }

            }
        }
    }
}