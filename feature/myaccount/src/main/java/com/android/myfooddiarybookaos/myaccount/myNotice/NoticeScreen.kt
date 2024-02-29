package com.android.myfooddiarybookaos.myaccount.myNotice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.myaccount.myNotice.item.NoticeItem
import com.android.myfooddiarybookaos.myaccount.viewModel.MyViewModel

@Composable
fun NoticeScreen(
    myNavi: NavHostController,
    viewModel: MyViewModel = hiltViewModel()
) {

    val noticePagingItems = viewModel.noticeList.collectAsLazyPagingItems()
    val isBackState = remember { mutableStateOf(false) }
    if (isBackState.value) {
        myNavi.popBackStack()
        isBackState.value = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 4.dp
                    )
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.BottomStart)
                        .padding(bottom = 2.dp)
                        .clickable(onClick = { isBackState.value = true }),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.chevron_left_24px_no_box),
                        contentDescription = "",
                    )
                }

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.BottomCenter)
                        .padding(14.75.dp)
                ) {
                    TextBox(
                        text = "공지사항",
                        fontWeight = 500,
                        fontFamily = robotoBold,
                        fontSize = 18.scaledSp(),
                        color = colorResource(id = R.color.black)
                    )
                }

            }

            Spacer(modifier = Modifier.height(1.dp))

            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .coloredInnerShadow(
                        color = colorResource(id = R.color.black_10),
                        offsetY = 1.dp,
                        blurRadius = 4.dp
                    )
            )

        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 50.dp)
                .background(Color.White),
        ) {
            items(noticePagingItems.itemCount) { index ->
                noticePagingItems[index]?.let { notice ->
                    NoticeItem(notice = notice.transNotice())
                }
            }
        }
    }
}
