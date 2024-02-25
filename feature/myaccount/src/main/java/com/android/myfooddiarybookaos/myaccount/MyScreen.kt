package com.android.myfooddiarybookaos.TabMyAccount

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.android.myfooddiarybookaos.myaccount.navi.MyGraph

@Composable
fun MyScreen(
    isUpdateView: MutableState<Boolean>,
) {
    val myNavi = rememberNavController()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MyGraph(
            myNavi = myNavi,
            isUpdateView = isUpdateView
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun PreviewMyScreen() {
    MyScreen(
        isUpdateView = mutableStateOf(false)
    )
}
