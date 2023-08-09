package com.android.myfooddiarybookaos.TabSearch

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.feature.search.R

@Composable
fun SearchScreen() {
    Column {
        Surface(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.size_20),
                end = dimensionResource(id = R.dimen.size_20),
                top = dimensionResource(id = R.dimen.size_33),
                bottom = dimensionResource(id = R.dimen.size_13),
            )
        ) {
            SearchBox()
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())

        ) {
            // 데이터 없음 표시
            Box(contentAlignment = Alignment.Center) {
                NotDataView()
            }
            SearchData()
        }
    }
}

// 검색 기록 뷰
@Composable
private fun SearchData(){

}

@Composable
private fun SearchBox() {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    // 검색 아이콘
    val trailingIconView = @Composable {
        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bottom3),
                contentDescription = "",

                Modifier
                    .height(dimensionResource(id = R.dimen.size_26))
                    .width(dimensionResource(id = R.dimen.size_26))
            )
        }
    }
    Row{
        Surface( // 배경
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4)),
            border = BorderStroke(
                dimensionResource(id = R.dimen.size_1),
                colorResource(id = R.color.black)
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
                        text = "식사일기 검색",
                        color = colorResource(id = R.color.weak_color),
                        fontSize = dimensionResource(id = R.dimen.size_14_sp).value.sp,
                    )
                },
                // 맨 앞 아이콘
                leadingIcon = trailingIconView
            )
        }

    }
}


@Preview
@Composable
fun SearchScreenPreview(){
    SearchScreen()
}