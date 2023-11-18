package com.android.myfooddiarybookaos.detail.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.customOuterShadow
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.model.detail.DiaryDetail

@Composable
fun DetailPopupScreen(
    isDropDown: MutableState<Boolean>,
    fixImage: () -> Unit,
    addImage: () -> Unit,
    fixMemo: () -> Unit,
    deleteDiary: () -> Unit
) {
    DropdownMenu(
        expanded = isDropDown.value,
        onDismissRequest = { isDropDown.value = false }) {
        Surface(
            modifier = Modifier
                .customOuterShadow(
                    color = colorResource(id = R.color.pop_up_shadow),
                    offsetY = 3.dp,
                    blurRadius = 8f
                )
                .border(1.dp, Color.Transparent, RoundedCornerShape(8.dp))
        ) {
            Card {
                Column {
                    Box(
                        modifier = Modifier
                            .width(140.dp)
                            .height(38.dp)
                            .padding(start = 12.dp)
                            .clickable { fixImage() },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "사진 수정",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.text_dark),
                            fontFamily = robotoRegular,
                            fontWeight = FontWeight.W500
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(140.dp)
                            .height(38.dp)
                            .padding(start = 12.dp)
                            .clickable { addImage() },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "사진 추가",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.text_dark),
                            fontFamily = robotoRegular,
                            fontWeight = FontWeight.W500
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(140.dp)
                            .height(38.dp)
                            .padding(start = 12.dp)
                            .clickable { fixMemo() },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "메모 수정",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.text_dark),
                            fontFamily = robotoRegular,
                            fontWeight = FontWeight.W500
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(140.dp)
                            .height(38.dp)
                            .padding(start = 12.dp)
                            .clickable { deleteDiary() },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "일기 삭제",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.not_valid_text_color),
                            fontFamily = robotoRegular,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            }
        }
    }
}
