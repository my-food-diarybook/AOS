package com.example.myfooddiarybookaos.Data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreen(
    val route : String,
    val title : String,
    val icon : ImageVector,
    val badgeCount : Int
){

}
