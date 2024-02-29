package com.android.myfooddiarybookaos.detail.mainUi.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.detail.tag.TagLayer
import com.android.myfooddiarybookaos.model.detail.Tag

@Composable
fun DetailTag(
    tags: List<Tag>?

) {
    if (tags == null || tags.isEmpty()) {
        Text(
            text = "#태그",
            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.W500)),
            fontSize = 18.scaledSp(),
            color = colorResource(id = R.color.calender_next_color),
        )
    } else {
        TagLayer(tags.toMutableList(), clickTag = {})
    }
}
