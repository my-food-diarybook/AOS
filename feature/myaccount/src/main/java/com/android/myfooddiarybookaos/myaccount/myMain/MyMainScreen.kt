package com.android.myfooddiarybookaos.myaccount.myMain

import android.content.pm.PackageManager
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.myfooddiarybookaos.api.UserInfoSharedPreferences
import com.android.myfooddiarybookaos.api.appVersion
import com.android.myfooddiarybookaos.core.data.BuildConfig
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.TextBox
import com.android.myfooddiarybookaos.data.component.coloredInnerShadow
import com.android.myfooddiarybookaos.data.robotoRegular
import com.android.myfooddiarybookaos.data.utils.scaledSp
import com.android.myfooddiarybookaos.myaccount.component.EmailInfo
import com.android.myfooddiarybookaos.myaccount.component.OptionBox
import com.android.myfooddiarybookaos.myaccount.component.Statistics
import com.android.myfooddiarybookaos.myaccount.component.Subject
import com.android.myfooddiarybookaos.myaccount.navi.MyScreenRoot

@Composable
fun MyMainScreen(
    myNavi: NavHostController,
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val email = remember { mutableStateOf(UserInfoSharedPreferences(context).userEmail ?: "") }
    val infoClickState = remember { mutableStateOf(false) }
    if (infoClickState.value) {
        myNavi.navigate(MyScreenRoot.INFO)
        infoClickState.value = false
    }
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
                fontSize = 18.scaledSp(),
                color = colorResource(id = R.color.black),
                lineHeight = 18.scaledSp(),
            )
        }
        Divider(
            modifier = Modifier
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
                    .clickable {
                        infoClickState.value = true
                    }
            ) {
                EmailInfo(email.value)
            }
            Subject("통계")
            Statistics()
            Subject("일반")
            Spacer(modifier = Modifier.height(7.dp))
            Box(
                modifier = Modifier.clickable { myNavi.navigate(MyScreenRoot.NOTICE) }
            ) {
                OptionBox("공지사항", R.drawable.right_side_my, null)
            }

            OptionBox("앱 버전 정보", null, appVersion)
            OptionBox("의견보내기", R.drawable.message, null)
            Spacer(modifier = Modifier.height(80.dp))

        }
    }
}