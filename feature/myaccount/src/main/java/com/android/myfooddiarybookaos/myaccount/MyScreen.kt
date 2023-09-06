package com.android.myfooddiarybookaos.TabMyAccount

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.coloredShadow
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.robotoRegular

@Composable
fun MyScreen() {
    Column {
        Box(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.size_90))
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.size_14_75)),
            contentAlignment = Alignment.BottomCenter
        ) {
            TextBox(
                text = "마이",
                fontWeight = 500,
                fontFamily = robotoRegular,
                fontSize = 18.sp,
                color = colorResource(id = R.color.black),
            )
        }
        Divider(modifier = Modifier
            .height(2.dp)
            .coloredShadow(
                color = colorResource(id = R.color.black_10),
                offsetY = 1.dp,
                blurRadius = 4.dp
            )
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
                        color = colorResource(id = R.color.line_color_deep),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4))
                    )
            ) {
                EmailInfo("user_email@gmail.com")
            }
            Subject("통계")
            Statistics()
            Subject("일반")
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_7)))
            OptionBox("공지사항", R.drawable.right_side_my, null)
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
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.size_7),
            end = dimensionResource(id = R.dimen.size_9_17),
            top = dimensionResource(id = R.dimen.size_17),
            bottom = dimensionResource(id = R.dimen.size_17)
        )
    ) {
        Box(modifier = Modifier
            .width(dimensionResource(id = R.dimen.size_270))
            .align(Alignment.CenterStart)
        ) {
            Text(
                text = email,
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                color = colorResource(id = R.color.black),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.size_24))
                .align(Alignment.CenterEnd)
            , contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.right_side_my),
                contentDescription = "",
            )
        }
    }
}

@Composable
private fun Subject(text: String) {
    TextBox(
        text = text,
        fontFamily = robotoBold,
        fontSize = 14.sp,
        fontWeight = 700,
        color = colorResource(id = R.color._1A1D1D)
    )
}

@Composable
private fun Statistics() {
    Box(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.size_3),
                bottom = dimensionResource(id = R.dimen.size_11)
            )
            .border(
                width = dimensionResource(id = R.dimen.size_1),
                color = colorResource(id = R.color.line_color_deep),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4))
            )
    ) {
        Column(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.size_9),
                end = dimensionResource(id = R.dimen.size_11)
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_19)))
            TextBox(
                text = "모든 식사 일기",
                fontWeight = 400,
                fontFamily = robotoRegular,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
            TextBox(
                text = "10",
                fontWeight = 700,
                fontFamily = robotoBold,
                fontSize = 28.sp,
                color = colorResource(id = R.color.main_color)
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_12)))
            InDivider()
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_15)))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (category in listOf("아침", "아점", "점심", "점저")) {
                    CategoryMenu(category, 0)
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_12)))
            InDivider()
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_12)))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (category in listOf("간식", "저녁", "야식", "기타")) {
                    CategoryMenu(category, 0)
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_22)))
        }
    }
}

@Composable
private fun InDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.size_1)),
        color = colorResource(id = R.color.line_color_deep)
    )
}

@Composable
private fun CategoryMenu(text: String, count: Int) {
    Surface(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.size_12))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            TextBox(
                text = text,
                fontWeight = 500,
                fontFamily = robotoRegular,
                fontSize = 16.sp,
                color = colorResource(id = R.color.black)
            )
            TextBox(
                text = count.toString(),
                fontWeight = 500,
                fontFamily = robotoRegular,
                fontSize = 28.sp,
                color = colorResource(id = R.color.line_color_deep)
            )
        }
    }
}

@Composable
private fun OptionBox(text: String, drawable: Int?, version: String?) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(id = R.dimen.size_9),
            )
            .border(
                width = dimensionResource(id = R.dimen.size_1),
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4))
            ),
    ) {
        Box(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.size_9),
                top = dimensionResource(id = R.dimen.size_17),
                bottom = dimensionResource(id = R.dimen.size_17)
            ),
        ) {
            Box(modifier = Modifier.align(Alignment.CenterStart)){
                TextBox(
                    text = text,
                    fontWeight = 500,
                    fontFamily = robotoRegular,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.black)
                )
            }

            if (drawable != null) {
                Box(modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = dimensionResource(id = R.dimen.size_12)),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id = drawable),
                        contentDescription = "",
                        colorFilter = if (drawable==R.drawable.right_side_my) ColorFilter.tint(colorResource(id = R.color.black_87))
                                    else ColorFilter.tint(colorResource(id = R.color.black))
                    )
                }
            }
            if (version != null) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = dimensionResource(id = R.dimen.size_12))
                ) {
                    TextBox(
                        text = version,
                        fontWeight = 500,
                        fontFamily = robotoRegular,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.black)
                    )
                }
            }
        }
    }
}