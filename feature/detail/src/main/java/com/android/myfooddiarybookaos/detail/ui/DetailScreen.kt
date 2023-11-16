package com.android.myfooddiarybookaos.detail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.data.state.ApplicationState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import com.android.myfooddiarybookaos.core.data.R
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.android.myfooddiarybookaos.data.path.byteStringToBitmap
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.detail.component.DetailTopLayer
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel

@Composable
fun DetailScreen(
    appState: ApplicationState,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val currentHeight = LocalConfiguration.current.screenHeightDp.dp
    LaunchedEffect(Unit) {
        detailViewModel.initAppState(appState)
    }

    Column {
        DetailTopLayer()

        Image(
            rememberAsyncImagePainter(null),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(499 * currentHeight / 800),
            contentScale = ContentScale.Crop,
        )

        Surface(
            modifier = Modifier.padding(start = 21.dp, top = 25.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(
                        1.dp,
                        colorResource(id = R.color.main_color),
                        RoundedCornerShape(4.dp)
                    ),
                contentAlignment = Alignment.Center
            ){
                Text(
                    color = colorResource(id = R.color.main_color),
                    modifier = Modifier.padding(
                        start = 9.dp, end = 8.dp,
                        top = 5.dp, bottom = 5.dp
                    ),
                    text = "null",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular,FontWeight.W100))
                )
            }
        }
    }
}