package com.android.myfooddiarybookaos.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.search.SearchViewModel
import com.android.myfooddiarybookaos.search.component.PagingDiaryComponent

@Composable
fun CategoryScreen(
    categoryName: MutableState<String>,
    categoryType: MutableState<String>,
    selectDiary: (diaryId: Int) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    if (categoryType.value.isNotEmpty()) {
        LaunchedEffect(Unit) {
            viewModel.getPagingDiaries(
                categoryName.value,
                categoryType.value
            )
        }
    }

    PagingDiaryComponent(
        categoryName = categoryName,
        selectDiary = {
            selectDiary(it.diaryId)
        }
    )
}
