package com.android.myfooddiarybookaos.TabMyAccount

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.ui.theme.TextBox

@Composable
fun MyScreen() {
    Column {
        Column(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.size_90))
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.size_14_75)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            TextBox(
                text = "마이",
                fontWeight = 500,
                fontFamily = Font(R.font.roboto_regular),
                fontSize = dimensionResource(id = R.dimen.size_18_sp).value.sp,
                color = colorResource(id = R.color.black),
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_1)),
            color = colorResource(id = R.color.line_color_deep)
        )
        // 상세 탭
        Column(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.size_20),
                    end = dimensionResource(id = R.dimen.size_20),
                    top = dimensionResource(id = R.dimen.size_12)
                )
                .fillMaxSize()
                // 스크롤 부여
                .verticalScroll(rememberScrollState())
        ) {
            Subject("내 정보")
            // 임시 이메일 -> 실제 이메일 전달
            Surface(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.size_3),
                        bottom = dimensionResource(id = R.dimen.size_12)
                    )
                    .border(
                        width = dimensionResource(id = R.dimen.size_1),
                        color = colorResource(id = R.color.black),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_10))
                    )
            ) {
                EmailInfo("user_email@gmail.com")
            }
            Subject("통계")
            Statistics()
            Subject("일반")
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_7)))
            OptionBox("공지사항", R.drawable.right_side, null)
            OptionBox("앱 버전 정보", null, "1. 1. 1")
            OptionBox("의견보내기", R.drawable.message, null)
        }
    }
}

@Preview
@Composable
fun PreviewMyScreen() {
    MyScreen()
}

@Composable
private fun EmailInfo(email: String) {
    Row(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.size_17))

    ) {
        TextBox(
            text = email,
            fontWeight = 500,
            fontFamily = Font(R.font.roboto_regular),
            fontSize = dimensionResource(id = R.dimen.size_20_sp).value.sp,
            color = colorResource(id = R.color.black)
        )
        Spacer(modifier = Modifier.weight(1f)) //우측 정렬
        Image(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.size_8))
                .height(dimensionResource(id = R.dimen.size_12)),
            painter = painterResource(id = R.drawable.right_side),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Black)
        )
    }
}

@Composable
private fun Subject(text: String) {
    TextBox(
        text = text,
        fontFamily = Font(R.font.roboto_regular),
        fontSize = dimensionResource(id = R.dimen.size_14_sp).value.sp,
        fontWeight = 700,
        color = colorResource(id = R.color.black)
    )
}

@Composable
private fun Statistics() {
    Column(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.size_3),
                bottom = dimensionResource(id = R.dimen.size_11)
            )
            .border(
                width = dimensionResource(id = R.dimen.size_1),
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_10))
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_12)))
        TextBox(
            text = "모든 식사 일기",
            fontWeight = 400,
            fontFamily = Font(R.font.roboto_regular),
            fontSize = dimensionResource(id = R.dimen.size_14_sp).value.sp,
            color = colorResource(id = R.color.black)
        )
        TextBox(
            text = "10",
            fontWeight = 700,
            fontFamily = Font(R.font.roboto_regular),
            fontSize = dimensionResource(id = R.dimen.size_28_sp).value.sp,
            color = colorResource(id = R.color.main_color)
        )
        InDivider()
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (category in listOf("아침", "아점", "점심", "점저")) {
                CategoryMenu(category, 0)
            }
        }
        InDivider()
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.size_34))

        ) {
            for (category in listOf("간식", "저녁", "야식", "기타")) {
                CategoryMenu(category, 0)
            }
        }

    }
}

@Composable
private fun InDivider() {
    Surface(
        Modifier.padding(
            start = dimensionResource(id = R.dimen.size_9),
            top = dimensionResource(id = R.dimen.size_12),
            end = dimensionResource(id = R.dimen.size_9),
            bottom = dimensionResource(id = R.dimen.size_15),
        )
    ) {
        Divider(
            Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_1)),
            color = colorResource(id = R.color.line_color_deep)
        )
    }
}

@Composable
private fun CategoryMenu(text: String, count: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextBox(
            text = text,
            fontWeight = 500,
            fontFamily = Font(R.font.roboto_regular),
            fontSize = dimensionResource(id = R.dimen.size_16_sp).value.sp,
            color = colorResource(id = R.color.black)
        )
        TextBox(
            text = count.toString(),
            fontWeight = 500,
            fontFamily = Font(R.font.roboto_regular),
            fontSize = dimensionResource(id = R.dimen.size_16_sp).value.sp,
            color = colorResource(id = R.color.black)
        )
    }
}

@Composable
private fun OptionBox(text: String, drawable: Int?, version: String?) {
    Surface(
        modifier = Modifier
            .padding(
                bottom = dimensionResource(id = R.dimen.size_9),
            )
            .border(
                width = dimensionResource(id = R.dimen.size_1),
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_10))
            ),
    ) {
        Row(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.size_17),
                bottom = dimensionResource(id = R.dimen.size_17),
                end = dimensionResource(id = R.dimen.size_12),
                start = dimensionResource(id = R.dimen.size_9)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBox(
                text = text,
                fontWeight = 500,
                fontFamily = Font(R.font.roboto_regular),
                fontSize = dimensionResource(id = R.dimen.size_20_sp).value.sp,
                color = colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.weight(1f)) //우측 정렬
            if (drawable != null) {
                Image(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.size_14)),
                    painter = painterResource(id = drawable),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }
            if (version != null) {
                TextBox(
                    text = version,
                    fontWeight = 500,
                    fontFamily = Font(R.font.roboto_regular),
                    fontSize = dimensionResource(id = R.dimen.size_20_sp).value.sp,
                    color = colorResource(id = R.color.black)
                )
            }
        }
    }
}