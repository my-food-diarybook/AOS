package com.android.myfooddiarybookaos.detail.galleryUi.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.model.image.GalleryImage
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun GalleryTopBar(
    selectedImages: List<GalleryImage>,
    currentDirectory: Pair<String, String?>,
    directories: List<Pair<String, String?>>,
    setCurrentDirectory: (Pair<String, String?>) -> Unit,
    backStage: () -> Unit,
    nextStage: () -> Unit
) {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    val nothingSelected = selectedImages.isEmpty()

    Box(
        Modifier
            .fillMaxWidth()
            .height(90.dp),
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .padding(bottom = 2.dp)
                .align(Alignment.BottomStart)
                .clickable { backStage() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.close),
                contentDescription = null
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 14.27.dp)
                .clickable {
                    isDropDownMenuExpanded = true
                }
        ) {
            Text(
                text = currentDirectory.first,
                color = Color.Black,
                fontSize = 18.scaledSp(),
                fontWeight = FontWeight.SemiBold,
                lineHeight = 18.scaledSp()
            )
            Icon(
                painterResource(id = R.drawable.bottom_arrow),
                modifier = Modifier
                    .rotate(if (isDropDownMenuExpanded) 180f else 0f), // 회전
                contentDescription = null,
            )
        }

        // 갤러리 선택 메뉴
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(
                    horizontal = 20.dp
                )
                .border(
                    BorderStroke(1.dp, Color.White.copy(alpha = 0.8f))
                ),
            expanded = isDropDownMenuExpanded,
            onDismissRequest = { isDropDownMenuExpanded = false}
        ) {
            directories.map{
                DropdownMenuItem(onClick = {
                    isDropDownMenuExpanded =false
                    setCurrentDirectory(it)
                }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it.first,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontFamily = robotoRegular,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            }
        }

        Text(
            text = "완료",
            color = if (nothingSelected) Color.Gray.copy(alpha = 0.7f)
            else colorResource(id = R.color.main_color),
            fontSize = 18.scaledSp(),
            fontWeight = FontWeight.SemiBold,
            lineHeight = 18.scaledSp(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 10.dp,
                    bottom = 15.dp
                )
                .clickable {
                    if (!nothingSelected) {
                        nextStage()
                    }
                }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GalleryTopBarPreview() {
    GalleryTopBar(
        listOf(),
        Pair("최근항목", null),
        listOf(),
        setCurrentDirectory = {},
        backStage = { },
        nextStage = { }
    )
}