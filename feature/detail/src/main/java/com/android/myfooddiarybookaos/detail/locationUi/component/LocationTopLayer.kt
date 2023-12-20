package com.android.myfooddiarybookaos.detail.locationUi.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationTopLayer(
    userInput: String,
    submitEnabled: State<Boolean>,
    search: (String) -> Unit,
    goBack: () -> Unit,
    onDelete: () -> Unit
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val trailingIconView = @Composable {
        if (submitEnabled.value) {
            IconButton(
                onClick = {
                    onDelete()
                }
            ) {
                Box(
                    modifier = Modifier.size(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.close_24px_copy),
                        contentDescription = null,
                    )
                }
            }
        } else {
            IconButton(onClick = { }) {
                Box(
                    modifier = Modifier.size(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = null,
                    )
                }
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 43.dp,
                start = 4.dp,
                end = 19.dp
            )
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clickable { goBack() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(id = R.drawable.main_left),
                contentDescription = null
            )
        }

        BasicTextField(
            value = userInput,
            onValueChange = {
                search(it)
            },
            modifier = Modifier
                .border(
                    1.dp,
                    Color.Black,
                    RoundedCornerShape(4.dp)
                )
                .fillMaxWidth()
                .wrapContentHeight(),
            interactionSource = interactionSource,
            cursorBrush = SolidColor(Color.Black),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.scaledSp(),
                lineHeight = 16.scaledSp(),
                fontWeight = FontWeight.W300,
                fontFamily = robotoRegular
            )
        ) {
            TextFieldDefaults.TextFieldDecorationBox(
                value = userInput,
                innerTextField = it,
                singleLine = true,
                trailingIcon = trailingIconView,
                placeholder = {
                    Text(
                        text = "위치 검색",
                        color = colorResource(id = R.color.weak_color),
                        fontSize = 16.scaledSp(),
                        lineHeight = 16.scaledSp(),
                        fontWeight = FontWeight.W300,
                        fontFamily = robotoRegular
                    )
                },
                enabled = true,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                interactionSource = interactionSource,
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                    start = 15.dp,
                    end = 6.dp,
                    top = 7.dp,
                    bottom = 7.dp
                ),
                visualTransformation = VisualTransformation.None
            )
        }
    }
}
