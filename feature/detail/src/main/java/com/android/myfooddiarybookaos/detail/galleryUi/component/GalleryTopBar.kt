package com.android.myfooddiarybookaos.detail.galleryUi.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.detail.viewModel.GalleryViewModel
import com.android.myfooddiarybookaos.model.image.GalleryImage

@Composable
fun GalleryTopBar(
    isMultiSelectView: Boolean,
    prevCount: Int,
    selectedImages: List<GalleryImage>,
    currentDirectory: Pair<String, String?>,
    directories: List<Pair<String, String?>>,
    setCurrentDirectory: (Pair<String, String?>) -> Unit,
    backStage: () -> Unit,
    nextStage: () -> Unit,
    viewModel: GalleryViewModel = hiltViewModel()
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
            onDismissRequest = { isDropDownMenuExpanded = false }
        ) {
            directories.map {
                DropdownMenuItem(onClick = {
                    isDropDownMenuExpanded = false
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
            text = if (isMultiSelectView) "${prevCount + viewModel.selectedImages.size} 등록" else "완료",
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
        false,
        prevCount = 0,
        listOf(),
        Pair("최근항목", null),
        listOf(),
        setCurrentDirectory = {},
        backStage = { },
        nextStage = { }
    )
}
