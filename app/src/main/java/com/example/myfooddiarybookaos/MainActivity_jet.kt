package com.example.myfooddiarybookaos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myfooddiarybookaos.ui.theme.MyFoodDiaryBookAOSTheme
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity_jet : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFoodDiaryBookAOSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainUi()
                }
            }
        }
    }
}

@Composable
fun MainUi() { 
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyFoodDiaryBookAOSTheme {
        MainUi()

    }
}