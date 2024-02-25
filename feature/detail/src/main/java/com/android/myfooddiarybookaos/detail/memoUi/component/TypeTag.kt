package com.android.myfooddiarybookaos.detail.memoUi.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.detail.tag.TagLayer
import com.android.myfooddiarybookaos.model.detail.Tag

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TypeTag(
    tags: MutableList<Tag>,
    addTag: (String) -> Unit,
    removeTag: (Tag) -> Unit
) {
    val newTagText = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val tagColor = animateColorAsState(
        if (newTagText.value.isEmpty()) colorResource(id = R.color.calender_next_color)
        else Color.Black
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = "#",
            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
            fontSize = 18.scaledSp(),
            color = tagColor.value,
            lineHeight = 18.scaledSp(),
        )

        BasicTextField(
            value = newTagText.value,
            onValueChange = { newTagText.value = it },
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
                fontSize = 18.scaledSp(),
                color = Color.Black,
                lineHeight = 18.scaledSp(),
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                addTag(newTagText.value)
                newTagText.value = ""
                keyboardController?.hide()
            }),
            modifier = Modifier
                .width(IntrinsicSize.Max),
            maxLines = 1,
            decorationBox = { innerTextField ->
                if (newTagText.value.isEmpty()) {
                    Text(
                        text = "태그",
                        fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
                        fontSize = 18.scaledSp(),
                        color = colorResource(id = R.color.calender_next_color),
                        maxLines = 1,
                        lineHeight = 18.scaledSp(),
                    )
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.width(4.dp))

        TagLayer(tags = tags, clickTag = {
            removeTag(it)
        })
    }
}
