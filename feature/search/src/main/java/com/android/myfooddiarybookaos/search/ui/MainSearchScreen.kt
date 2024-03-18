package com.android.myfooddiarybookaos.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.myfooddiarybookaos.data.component.ErrorPage
import com.android.myfooddiarybookaos.data.component.LoadPage
import com.android.myfooddiarybookaos.data.state.LoadState
import com.android.myfooddiarybookaos.model.search.SearchDiary
import com.android.myfooddiarybookaos.search.SearchViewModel
import com.android.myfooddiarybookaos.search.component.PagingCategoryComponent
import com.android.myfooddiarybookaos.search.component.SearchCategoryComponent
import com.android.myfooddiarybookaos.search.state.SearchState

@Composable
fun MainSearchScreen(
    isUpdateView: MutableState<Boolean>,
    searchState: MutableState<SearchState>,
    queryChangeState: MutableState<Boolean>,
    searchQuery: MutableState<TextFieldValue>,
    selectItem: (SearchDiary) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    when (searchState.value) {
        SearchState.MAIN_SEARCH -> {
            if (isUpdateView.value) {
                viewModel.getPagingCategories()
                isUpdateView.value = false
            }
            LaunchedEffect(Unit) {
                viewModel.getPagingCategories()
            }
            val pagingItems = viewModel.pagingCategoryList.collectAsLazyPagingItems()
            when (state.value) {
                LoadState.Init -> {
                    PagingCategoryComponent(
                        selectItem = selectItem,
                        pagingItems = pagingItems
                    )
                }

                LoadState.Loading -> {
                    LoadPage()
                }

                LoadState.Fail -> {
                    ErrorPage {
                        viewModel.getPagingCategories()
                    }
                }
            }
        }

        SearchState.QUERY_SEARCH -> {
            if (queryChangeState.value) {
                viewModel.getSearchData(searchQuery.value.text)
                queryChangeState.value = false
            }
            val searchItems = viewModel.searchCategoryList.collectAsState()
            when(state.value){
                LoadState.Init -> {
                    SearchCategoryComponent(
                        searchItems = searchItems,
                        selectItem = selectItem
                    )
                }
                LoadState.Loading -> {
                    LoadPage()
                }
                LoadState.Fail -> {
                    ErrorPage {
                        viewModel.getSearchData(searchQuery.value.text)
                    }
                }
            }
        }
    }
}
