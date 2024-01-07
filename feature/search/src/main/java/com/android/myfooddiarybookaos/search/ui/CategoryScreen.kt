package com.android.myfooddiarybookaos.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.search.SearchViewModel
import com.android.myfooddiarybookaos.search.component.PagingDiaryComponent
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

@Composable
fun CategoryScreen(
    categoryName: MutableState<String>,
    categoryType: MutableState<String>,
    selectDiary: (diaryId:Int) -> Unit,
    viewModel : SearchViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getPagingDiaries(
            categoryName.value,
            categoryType.value
        )
    }

    PagingDiaryComponent(
        categoryName = categoryName,
        selectDiary = {
            selectDiary(it.diaryId)
        }
    )
}