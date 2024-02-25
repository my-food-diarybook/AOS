package com.android.myfooddiarybookaos.detail.mainUi.imageSlider

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.detail.mainUi.item.OneDetailImage
import com.android.myfooddiarybookaos.model.image.Image


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSliderScreen(
    images: List<Image>,
    pagerState: PagerState
) {
    val imageHeight = 499.dp * LocalConfiguration.current.screenHeightDp / 800

    Box(
        Modifier
            .fillMaxWidth()
            .height(imageHeight)
    ) {
        HorizontalPager(
            state = pagerState
        ) { page ->
            OneDetailImage(image = images[page])
        }

        if (images.size > 1) {
            Box(
                modifier = Modifier
                    .padding(
                        top = 19.dp, end = 15.dp
                    )
                    .background(Color.Transparent)
                    .align(Alignment.TopEnd),
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
                        fontSize = 18.scaledSp(),
                        fontFamily = robotoBold,
                        fontWeight = FontWeight.W700,
                        color = Color.White,
                        lineHeight = 18.scaledSp(),
                    )
                }
            }
        }
    }
}
