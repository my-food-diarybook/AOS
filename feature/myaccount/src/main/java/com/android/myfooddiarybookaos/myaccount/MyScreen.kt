package com.android.myfooddiarybookaos.TabMyAccount

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.android.myfooddiarybookaos.myaccount.navi.MyGraph

@Composable
fun MyScreen() {
    val myNavi = rememberNavController()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MyGraph(myNavi = myNavi)
    }
}

@Preview
@Composable
fun PreviewMyScreen() {
    MyScreen()
}
