package com.android.myfooddiarybookaos.detail.locationUi.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.myfooddiarybookaos.detail.function.DiaryViewState
import com.android.myfooddiarybookaos.detail.state.DetailFixState
import com.android.myfooddiarybookaos.core.data.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.android.myfooddiarybookaos.detail.locationUi.component.LocationTopLayer

@Composable
fun DetailLocationScreen(
    diaryFixState: DetailFixState,
    currentViewState: MutableState<DiaryViewState>
) {
    val searchLocation = remember { mutableStateOf("") }

    // 뒤로가기 제어
    BackHandler(enabled = true, onBack = {
        currentViewState.value = DiaryViewState.MEMO
    })

    Column {
        LocationTopLayer(
            location = {
                searchLocation.value = it
            },
            goBack = {
                currentViewState.value = DiaryViewState.MEMO
            }
        )
    }
}
