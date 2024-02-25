package com.android.myfooddiarybookaos.data.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.utils.scaledSp

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)


private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


@Composable
fun MyFoodDiaryBookAOSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}


// edit text 디자인
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditTextBox(
    hintText: String,
    editText: MutableState<TextFieldValue>,
    strokeColor: MutableState<Boolean>
) {
    var passwordView by remember {
        mutableStateOf(false)
    }
    // Composable icon 생성 -> 비밀번호 표시 아이콘
    val trailingIconView = @Composable {
        if (hintText == "비밀번호") {
            IconButton(
                onClick = {
                    passwordView = !passwordView
                }
            ) {
                Box(
                    modifier = Modifier.size(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eye_off_black),
                        contentDescription = "",
                        Modifier.size(14.dp),
                        tint = colorResource(id = R.color.text_dark)
                    )
                }
            }
        } else {
            Box(modifier = Modifier.size(20.dp))
        }
    }

    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Row {
        Surface( // 배경
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(
                1.dp,
                color =
                if (strokeColor.value) colorResource(id = R.color.weak_color)
                else colorResource(id = R.color.not_valid_text_color)
            ),
            color = colorResource(id = R.color.white)
        ) {
            BasicTextField(
                value = editText.value,
                onValueChange = { editText.value = it }, //값 변경 시 동작
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                interactionSource = interactionSource,
                visualTransformation = if (editText.value.text != "" && hintText == "비밀번호" && !passwordView)
                    PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
            ) {
                TextFieldDefaults.TextFieldDecorationBox(
                    value = editText.value.text,
                    innerTextField = it,
                    singleLine = true,
                    visualTransformation = if (editText.value.text != "" && hintText == "비밀번호" && !passwordView)
                        PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = trailingIconView,
                    placeholder = {
                        Text(
                            text = hintText,
                            color = colorResource(id = R.color.weak_color),
                            fontSize = 14.scaledSp(),
                        )
                    },
                    enabled = true,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    interactionSource = interactionSource,
                    contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 11.5.dp,
                        bottom = 11.5.dp
                    )
                )
            }
        }

    }
}

@Composable
fun TextBox(
    text: String,
    fontWeight: Int,
    fontFamily: Font,
    fontSize: TextUnit,
    color: Color,
) {
    Text(
        text = text,
        fontWeight = FontWeight(fontWeight),
        fontFamily = FontFamily(fontFamily),
        fontSize = fontSize,
        color = color
    )
}
