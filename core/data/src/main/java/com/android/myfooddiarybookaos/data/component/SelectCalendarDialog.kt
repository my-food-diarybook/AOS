package com.android.myfooddiarybookaos.Dialog

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.android.myfooddiarybookaos.core.data.R
@Composable
fun SelectCalendarDialog(
    todayViewModel : com.android.myfooddiarybookaos.data.todayViewModel.TodayViewModelInterface,
    isTopLayoutClick : (Boolean) -> Unit,
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
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_8))
        ) {
            SelectCalendarScreen(
                todayViewModel,
                isTopLayoutClick
            )
        }

    }
}