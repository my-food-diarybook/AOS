package com.android.myfooddiarybookaos.detail.locationUi.item

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import com.android.myfooddiarybookaos.model.map.Place
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.substring
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.utils.scaledSp

@Composable
fun SearchResultItem(
    query: String,
    place: Place,
    onSelected: () -> Unit
) {
    val placeDistance by remember {
        mutableStateOf(
            getDistance(place.distance)
        )
    }
    val indexList by remember {
        mutableStateOf(
            query.indexOfAll(place.place_name)
        )
    }
    val currentTextSize by remember {
        mutableStateOf(
            place.place_name.length
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onSelected() }
    ) {
        Text(
            text = buildAnnotatedString {
                if (indexList.isEmpty()) {
                    append(place.place_name)
                } else {
                    indexList.forEach { idx ->
                        withStyle(
                            SpanStyle(color = colorResource(id = R.color.main_color))
                        ) {
                            append(
                                place.place_name.substring(
                                    indexList[idx],
                                    indexList[idx] + currentTextSize
                                )
                            )
                        }
                        if (idx == indexList.size - 1) {
                            append(place.place_name.substring(indexList[idx] + currentTextSize))
                        } else {
                            append(
                                place.place_name.substring(
                                    indexList[idx] + currentTextSize,
                                    indexList[idx + 1]
                                )
                            )
                        }
                    }
                }
            },
            fontFamily = robotoRegular,
            fontSize = 18.scaledSp(),
            fontWeight = FontWeight.W500,
            color = Color.Black,
            lineHeight = 18.scaledSp()
        )

        Row(
            modifier = Modifier.wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = place.address_name,
                fontWeight = FontWeight.W500,
                fontSize = 12.scaledSp(),
                fontFamily = robotoRegular,
                color = colorResource(id = R.color.line_color_deep),
                lineHeight = 12.scaledSp(),
            )
            Spacer(modifier = Modifier.width(5.dp))
            Divider(
                Modifier
                    .height(12.dp)
                    .width(1.dp)
                    .background(colorResource(id = R.color.line_color_deep))
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = placeDistance,
                fontWeight = FontWeight.W500,
                fontSize = 12.scaledSp(),
                fontFamily = robotoRegular,
                color = colorResource(id = R.color.line_color_deep),
                lineHeight = 12.scaledSp(),
            )
        }
    }
}

fun String.indexOfAll(str: String): MutableList<Int> {
    var index = this.indexOf(str)
    val returnIndex = mutableListOf<Int>()

    while (index != -1) {
        returnIndex.add(index)
        index = this.indexOf(str, index + 1)
    }
    return returnIndex
}

fun getDistance(input: String? ): String {
    return try {
        (input!!.toDouble() / 1000).toInt().toString() + "km"
    } catch (e: java.lang.Exception){
      ""
    }
}