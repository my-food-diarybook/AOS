package com.android.myfooddiarybookaos.login

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.core.data.R
import com.android.myfooddiarybookaos.data.ui.theme.EditTextBox
import com.android.myfooddiarybookaos.data.ui.theme.MyFoodDiaryBookAOSTheme
import com.android.myfooddiarybookaos.data.ui.theme.TextBox
import com.android.myfooddiarybookaos.login.mainSubUi.BottomLayout
import com.android.myfooddiarybookaos.login.mainSubUi.MidLayout
import com.android.myfooddiarybookaos.login.mainSubUi.TopLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFoodDiaryBookAOSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TopLayout()
                        MidLayout()
                        BottomLayout()
                        Button(
                            onClick = {
//                                PasswordChangeFragment().show(
//                                    supportFragmentManager,""
//                                )
                            },
                            modifier = Modifier.wrapContentSize()
                        ){
                            Text("비밀번호 변경(test)")
                        }

                        Button(
                            onClick = {
//                                PasswordFindFragment().show(
//                                    supportFragmentManager,""
//                                )
                            },
                            modifier = Modifier.wrapContentSize()
                        ){
                            Text("비밀번호 찾기(test)")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyFoodDiaryBookAOSTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TopLayout()
                MidLayout()
                BottomLayout()
            }
        }
    }
}