package com.android.myfooddiarybookaos.search

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.search.component.PagingCategoryComponent
import com.android.myfooddiarybookaos.search.component.PagingDiaryComponent
import com.android.myfooddiarybookaos.search.component.SearchBox
import com.android.myfooddiarybookaos.search.component.SearchCategoryComponent
import com.android.myfooddiarybookaos.search.state.SearchState
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

@Composable
fun SearchScreen(
    appState: ApplicationState,
    diaryState: DiaryState,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }
    val categoryName = remember { mutableStateOf("") }
    val categoryType = remember { mutableStateOf("") }
    val searchState = remember { mutableStateOf(SearchState.MAIN_SEARCH) }

    if (searchQuery.value.text.isNotEmpty() && searchState.value != SearchState.DIARY_SEARCH) {
        searchState.value = SearchState.QUERY_SEARCH
    }
    if (searchQuery.value.text.isEmpty()) {
        searchState.value = SearchState.MAIN_SEARCH
    }

    Column {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 33.dp,
                    bottom = 13.dp
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            SearchBox(
                searchQuery,
                searchState,
                onQueryChange = {
                    viewModel.getSearchData(searchQuery.value.text)
                }
            )
        }
        when (searchState.value) {
            SearchState.MAIN_SEARCH -> {
                LaunchedEffect(Unit) {
                    viewModel.getPagingCategories()
                }
                PagingCategoryComponent(
                    searchSelect = {
                        categoryName.value = it.categoryName
                        categoryType.value = it.categoryType
                        searchState.value = SearchState.DIARY_SEARCH
                        searchQuery.value = TextFieldValue(it.categoryName)
                    }
                )
            }
            SearchState.QUERY_SEARCH -> {
                SearchCategoryComponent(
                    searchSelect = {
                        categoryName.value = it.categoryName
                        categoryType.value = it.categoryType
                        searchState.value = SearchState.DIARY_SEARCH
                        searchQuery.value = TextFieldValue(it.categoryName)
                    }
                )
            }
            SearchState.DIARY_SEARCH -> {
                LaunchedEffect(Unit) {
                    searchQuery.value = TextFieldValue(categoryName.value)
                    viewModel.getPagingDiaries(
                        categoryName.value,
                        categoryType.value
                    )
                }
                PagingDiaryComponent(
                    categoryName = categoryName,
                    selectDiary = {
                        diaryState.setDiaryDetail(it.diaryId)
                        appState.navController.navigate(ScreenRoot.DETAIL_DIARY)
                    }
                )
            }
        }

    }
}
