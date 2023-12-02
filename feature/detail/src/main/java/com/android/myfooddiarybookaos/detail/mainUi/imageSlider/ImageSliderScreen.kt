package com.android.myfooddiarybookaos.detail.mainUi.imageSlider

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.*
import com.android.myfooddiarybookaos.core.data.R
import androidx.compose.foundation.pager.HorizontalPager
import com.google.accompanist.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.detail.mainUi.item.OneDetailImage
import com.android.myfooddiarybookaos.model.image.Image

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSliderScreen(
    images: List<Image>?,
    currentViewImageId: MutableState<Int>
) {
    val imageHeight = 499.dp * LocalConfiguration.current.screenHeightDp / 800

    Box(
        Modifier
            .fillMaxWidth()
            .height(imageHeight)
    ) {
        images?.let {
            val pagerState = rememberPagerState(pageCount = { images.size })

            HorizontalPager(
                state = pagerState
            ) { page ->
                OneDetailImage(image = images[page])
                currentViewImageId.value = images[page].imageId
            }

            if (images.size > 1) {
                Surface(
                    modifier = Modifier
                        .padding(
                            top = 19.dp, end = 15.dp
                        )
                        .align(Alignment.TopEnd)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = colorResource(id = R.color.main_color_50),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .width(60.dp)
                            .height(30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${pagerState.currentPage + 1} / ${images.size}",
                            fontSize = 18.sp,
                            fontFamily = robotoBold,
                            fontWeight = FontWeight.W700,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}