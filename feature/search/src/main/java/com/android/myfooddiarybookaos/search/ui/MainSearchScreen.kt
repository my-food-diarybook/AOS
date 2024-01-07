package com.android.myfooddiarybookaos.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.model.search.SearchCategory
import com.android.myfooddiarybookaos.search.SearchViewModel
import com.android.myfooddiarybookaos.search.component.PagingCategoryComponent
import com.android.myfooddiarybookaos.search.component.PagingDiaryComponent
import com.android.myfooddiarybookaos.search.component.SearchCategoryComponent
import com.android.myfooddiarybookaos.search.state.SearchState
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

@Composable
fun MainSearchScreen(
    searchState : MutableState<SearchState>,
    select: (SearchCategory) -> Unit,
    viewModel : SearchViewModel = hiltViewModel()
) {
    when (searchState.value) {
        SearchState.MAIN_SEARCH -> {
            LaunchedEffect(Unit) {
                viewModel.getPagingCategories()
            }
            PagingCategoryComponent(
                searchSelect = {
                    select(it)
                }
            )
        }
        SearchState.QUERY_SEARCH -> {
            SearchCategoryComponent(
                searchSelect = {
                    select(it)
                }
            )
        }
    }


}