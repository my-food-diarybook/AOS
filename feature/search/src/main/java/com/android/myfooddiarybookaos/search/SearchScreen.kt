package com.android.myfooddiarybookaos.TabSearch

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.core.data.R

@Composable
fun SearchScreen() {
    Column {
        Box(
            modifier = Modifier.padding(
                bottom = dimensionResource(id = R.dimen.size_13),
            ),
            contentAlignment = Alignment.BottomCenter
        ) {
            SearchBox()
        }

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            // 데이터 없음 표시
            NotDataView()
//            SearchData()
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
            Box(modifier = Modifier
                .size(dimensionResource(id = R.dimen.size_40)),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "",
                )
            }
        }
    }
    Surface( // 배경
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4)),
        border = BorderStroke(
            dimensionResource(id = R.dimen.size_1),
            colorResource(id = R.color.black)
        ),
        color = colorResource(id = R.color.white),

    ) {
        TextField(
            value = text,
            onValueChange = { newText -> text = newText }, //값 변경 시 동작
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.white),
                textColor = colorResource(id = R.color.black)
            ),
            placeholder = {
                Text(
                    text = "식사일기 검색",
                    color = colorResource(id = R.color.calender_next_color),
                    fontSize = dimensionResource(id = R.dimen.size_16_sp).value.sp,
                )
            },
            // 맨 앞 아이콘
            leadingIcon = trailingIconView
        )
    }
}


@Preview
@Composable
fun SearchScreenPreview(){
    SearchScreen()
}