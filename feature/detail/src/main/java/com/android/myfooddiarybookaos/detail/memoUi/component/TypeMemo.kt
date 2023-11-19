package com.android.myfooddiarybookaos.detail.memoUi.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R

@Composable
fun TypeMemo(
    text: MutableState<String>,
    editMemo: (String) -> Unit
) {
    val memoText = remember { text }
    BasicTextField(
        value = memoText.value,
        onValueChange = {
            memoText.value = it
            editMemo(it)
        },
        textStyle = TextStyle(
            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
            fontSize = 18.sp,
            color = Color.Black,
        ),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        maxLines = 5,
        decorationBox = { innerTextField ->
            if (text.value.isEmpty()) {
                Text(
                    text = "메모 남기기",
                    fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.calender_next_color),
                )
            }
            innerTextField()
        }
    )

}