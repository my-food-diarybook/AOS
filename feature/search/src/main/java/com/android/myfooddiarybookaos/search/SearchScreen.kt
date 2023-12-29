package com.android.myfooddiarybookaos.TabSearch

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.search.component.SearchBox
import com.android.myfooddiarybookaos.search.state.SearchState

@Composable
fun SearchScreen() {

    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }
    val categoryName = remember { mutableStateOf("") }
    val categoryType = remember { mutableStateOf("") }
    val searchState = remember { SearchState.MAIN_SEARCH }
    Column {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 33.dp,
                    bottom = 13.dp
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            SearchBox(searchQuery)
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
private fun SearchData() {

}



@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}