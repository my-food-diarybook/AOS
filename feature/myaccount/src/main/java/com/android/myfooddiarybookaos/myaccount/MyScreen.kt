package com.android.myfooddiarybookaos.TabMyAccount

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.myaccount.viewModel.MyViewModel

@Composable
fun MyScreen(
    viewModel : MyViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val noticePagingItems = viewModel.noticeList.collectAsLazyPagingItems()
    Log.d("wlejflewjlwefjflwe",noticePagingItems.itemSnapshotList.items.toString())
    Column {
        Box(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .padding(bottom = 14.75.dp),
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
            .coloredInnerShadow(
                color = colorResource(id = R.color.black_10),
                offsetY = 1.dp,
                blurRadius = 4.dp
            )
        )


        // 상세 탭
        Column(
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 12.dp
                )
                .fillMaxSize()
                // 스크롤 부여
                .verticalScroll(scrollState)
        ) {
            Subject("내 정보")
            // 임시 이메일 -> 실제 이메일 전달
            Surface(
                modifier = Modifier
                    .padding(
                        top = 3.dp,
                        bottom = 12.dp
                    )
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.line_color_deep),
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                EmailInfo("user_email@gmail.com")
            }
            Subject("통계")
            Statistics()
            Subject("일반")
            Spacer(modifier = Modifier.height(7.dp))
            OptionBox("공지사항", R.drawable.right_side_my, null)
            OptionBox("앱 버전 정보", null, "1. 1. 1")
            OptionBox("의견보내기", R.drawable.message, null)
            Spacer(modifier = Modifier.height(80.dp))
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
            start = 7.dp,
            end = 9.17.dp,
            top = 17.dp,
            bottom = 17.dp
        )
    ) {
        Box(modifier = Modifier
            .width(270.dp)
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
                .size(24.dp)
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
                top = 3.dp,
                bottom = 11.dp
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.line_color_deep),
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(
                start = 9.dp,
                end = 11.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(19.dp))
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
            Spacer(modifier = Modifier.height(12.dp))
            InDivider()
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (category in listOf("아침", "아점", "점심", "점저")) {
                    CategoryMenu(category, 0)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            InDivider()
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (category in listOf("간식", "저녁", "야식", "기타")) {
                    CategoryMenu(category, 0)
                }
            }
            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}

@Composable
private fun InDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = colorResource(id = R.color.line_color_deep)
    )
}

@Composable
private fun CategoryMenu(text: String, count: Int) {
    Surface(
        modifier = Modifier.padding(12.dp)
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
                bottom = 9.dp,
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.black),
                shape = RoundedCornerShape(4.dp)
            ),
    ) {
        Box(
            modifier = Modifier.padding(
                start = 9.dp,
                top = 17.dp,
                bottom = 17.dp
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
                    .padding(end = 12.dp),
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
                        .padding(end = 12.dp)
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