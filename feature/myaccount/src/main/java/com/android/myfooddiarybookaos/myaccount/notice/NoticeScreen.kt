package com.android.myfooddiarybookaos.myaccount.notice

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.data.robotoBold
import com.android.myfooddiarybookaos.myaccount.notice.item.NoticeItem
import com.android.myfooddiarybookaos.myaccount.viewModel.MyViewModel
import java.util.*

@Composable
fun NoticeScreen(
    myNavi: NavHostController,
    viewModel : MyViewModel = hiltViewModel()
) {

    val noticePagingItems = viewModel.noticeList.collectAsLazyPagingItems()
    val isBackState = remember { mutableStateOf(false) }
    if (isBackState.value){
        myNavi.popBackStack()
        isBackState.value = false
    }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 50.dp)
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
                        painter = painterResource(id = R.drawable.chevron_left_24px),
                        contentDescription = "",
                    )
                }

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.BottomCenter)
                        .padding(14.75.dp)
                ){
                    TextBox(
                        text = "공지사항",
                        fontWeight = 500,
                        fontFamily = robotoBold,
                        fontSize = 18.sp,
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
                .background(Color.White),
        ){
            items(noticePagingItems.itemCount){ index ->
                noticePagingItems[index]?.let { notice ->
                    NoticeItem(notice = notice.transNotice())
                }
            }
        }
    }
}