package com.android.myfooddiarybookaos.data

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R

@Composable
fun EditTextBox(hintText: String) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    // Composable icon 생성 -> 비밀번호 표시 아이콘
    val trailingIconView = @Composable {
        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_eye_off_black),
                contentDescription = "",
                Modifier
                    .height(dimensionResource(id = R.dimen.size_14))
                    .width(dimensionResource(id = R.dimen.size_14))
            )
        }
    }
    Row{
        Surface( // 배경
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.size_16),
                end =  dimensionResource(id = R.dimen.size_16),
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4)),
            border = BorderStroke(
                dimensionResource(id = R.dimen.size_1),
                colorResource(id = R.color.weak_color)
            ),
            color = colorResource(id = R.color.white)
        ) {
            TextField(
                value = text,
                onValueChange = { newText -> text = newText }, //값 변경 시 동작
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.size_48)),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = colorResource(id = R.color.white)),
                placeholder = {
                    Text(
                        text = hintText,
                        color = colorResource(id = R.color.weak_color),
                        fontSize = dimensionResource(id = R.dimen.size_14_sp).value.sp,
                    )
                }, //힌트 (텍스트 ,칼라 적용 )
                trailingIcon = if (hintText == "비밀번호") trailingIconView else null,
                // 텍스트 암호화
                visualTransformation = if (text.text != "" && hintText == "비밀번호")
                    PasswordVisualTransformation() else VisualTransformation.None
            )
        }

    }
}
