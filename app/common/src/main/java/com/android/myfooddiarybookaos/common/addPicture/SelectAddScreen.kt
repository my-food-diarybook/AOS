package com.android.myfooddiarybookaos.common.addPicture

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.common.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.robotoLight

// 사진 촬영, 사진 선택
@Composable
fun SelectAddScreen(closeLog: () -> Unit) {
    // 사진 찍기 view
    var takePicClick by remember {
        mutableStateOf(false)
    }
    if (takePicClick){
        TakePhotoFromCameraLauncher(callback = {
            Log.d("bitmap : ",it.toString())
            takePicClick = false
        })
    }
    // 앨번 선택 view
    var takeAlbum by remember {
        mutableStateOf(false)
    }
    if (takeAlbum){
        SelectPhotoFromAlbumLauncher(callback = {
            Log.d("selected : ",it.toString())
            takeAlbum = false
        })
    }

    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    colorResource(id = R.color.light_back_color),
                    RoundedCornerShape(dimensionResource(id = R.dimen.size_13))
                )
                .padding()

        ) {

            Spacer(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.size_12))
                    .background(colorResource(id = R.color.light_line_color))
            )

            TextBox(
                text = "사진 추가",
                fontWeight = 400,
                fontFamily = robotoLight,
                fontSize = dimensionResource(id = R.dimen.size_13_sp).value.sp,
                color = colorResource(id = R.color.light_text_color)
            )
            TextBox(
                text = "사진 촬영 또는 사진 선택을 클릭해주세요.",
                fontWeight = 400,
                fontFamily = robotoLight,
                fontSize = dimensionResource(id = R.dimen.size_13_sp).value.sp,
                color = colorResource(id = R.color.light_text_color)
            )

            Spacer(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.size_12))
                    .background(colorResource(id = R.color.light_line_color))
            )

            Divider(
                modifier = Modifier.height(dimensionResource(id = R.dimen.size_1)),
                color = colorResource(id = R.color.light_line_color)
            )

            // 사진 촬영
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable {
                        takePicClick = true
                        closeLog()
                    }
            ) {
                Text(
                    text = "사진 촬영",
                    fontWeight = FontWeight(400),
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = dimensionResource(id = R.dimen.size_20_sp).value.sp,
                    color = colorResource(id = R.color.blue_text_color),
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.size_18),
                        bottom = dimensionResource(id = R.dimen.size_18),
                    )
                )
            }
            Divider(
                modifier = Modifier.height(dimensionResource(id = R.dimen.size_1)),
                color = colorResource(id = R.color.light_line_color)
            )
            // 사진 선택
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable {
                        takeAlbum = true
                        closeLog()
                    }
            ) {
                Text(
                    text = "사진 선택",
                    fontWeight = FontWeight(400),
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = dimensionResource(id = R.dimen.size_20_sp).value.sp,
                    color = colorResource(id = R.color.blue_text_color),
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.size_18),
                        bottom = dimensionResource(id = R.dimen.size_18),
                    )
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.size_8))
                .background(Color.Transparent)
        )

        // 취소
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White, RoundedCornerShape(dimensionResource(id = R.dimen.size_8)))
                .clickable {
                    closeLog()
                }
        ) {
            Text(
                text = "취소",
                fontWeight = FontWeight(400),
                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                fontSize = dimensionResource(id = R.dimen.size_20_sp).value.sp,
                color = colorResource(id = R.color.blue_text_color),
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.size_18),
                        bottom = dimensionResource(id = R.dimen.size_18),
                    )
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSelectScreen(){
    SelectAddScreen(closeLog = {})
}



