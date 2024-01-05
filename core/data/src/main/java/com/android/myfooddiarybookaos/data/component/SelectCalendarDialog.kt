package com.android.myfooddiarybookaos.data.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.android.myfooddiarybookaos.data.component.SelectCalendarScreen

@Composable
fun SelectCalendarDialog(
    isTopLayoutClick : (Boolean) -> Unit,
    changeAllData:() -> Unit
){

    Dialog(
        onDismissRequest = {
            isTopLayoutClick(false)
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(8.dp)
        ) {
            SelectCalendarScreen(
                isTopLayoutClick = isTopLayoutClick,
                dataChange = {
                    changeAllData()
                }
            )
        }

    }
}