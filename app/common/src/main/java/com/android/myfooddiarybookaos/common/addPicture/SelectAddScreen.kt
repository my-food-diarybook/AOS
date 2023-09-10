package com.android.myfooddiarybookaos.common.addPicture

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
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
import java.io.File

// https://sungbin.land/jetpack-compose-%EA%B0%A4%EB%9F%AC%EB%A6%AC-%EC%B9%B4%EB%A9%94%EB%9D%BC-%EC%97%90%EC%84%9C-%EC%82%AC%EC%A7%84-%EA%B0%80%EC%A0%B8%EC%98%A4%EA%B8%B0-cf517eaca8bd
// 사진 촬영, 사진 선택
@Composable
fun SelectAddScreen(closeLog: () -> Unit) {
    val context = LocalContext.current.applicationContext
    // 사진 찍기 view
    var takePicClick by remember {
        mutableStateOf(false)
    }
    // 앨번 선택 view
    var takeAlbum by remember {
        mutableStateOf(false)
    }

    // 카메라 권한
    val permissionCameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        if (it){ takePicClick = true }
    }

    // 앨범 권한
    val perMissionAlbumLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        if (it){ takeAlbum = true }
    }

    // 촬영 동작
    if (takePicClick){
        TakePhotoFromCameraLauncher(callback = {
            Log.d("bitmap : ",it.toString())
            Log.d("getUriDate",makeUriDate().toString())
            takePicClick = false
            closeLog()
        })
    }

    // 앨범 선택 동작
    if (takeAlbum){
        SelectPhotoFromAlbumLauncher(callback = {
            Log.d("selected : ",it.toString())
            Log.d("getUriDate",getUriDate(it!!, context).toString())
            takeAlbum = false
            closeLog()
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
                fontSize = 13.sp,
                color = colorResource(id = R.color.light_text_color)
            )
            TextBox(
                text = "사진 촬영 또는 사진 선택을 클릭해주세요.",
                fontWeight = 400,
                fontFamily = robotoLight,
                fontSize = 13.sp,
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
                        permissionCameraLauncher.launch(
                            Manifest.permission.CAMERA
                        )
                    }
            ) {
                Text(
                    text = "사진 촬영",
                    fontWeight = FontWeight(400),
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 20.sp,
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
                        perMissionAlbumLauncher.launch(
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    }
            ) {
                Text(
                    text = "사진 선택",
                    fontWeight = FontWeight(400),
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontSize = 20.sp,
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
                fontSize = 20.sp,
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



