package com.android.myfooddiarybookaos.search

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.search.component.PagingCategoryComponent
import com.android.myfooddiarybookaos.search.component.PagingDiaryComponent
import com.android.myfooddiarybookaos.search.component.SearchBox
import com.android.myfooddiarybookaos.search.component.SearchCategoryComponent
import com.android.myfooddiarybookaos.search.navi.NavigationGraph
import com.android.myfooddiarybookaos.search.state.SearchState
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

@Composable
fun SearchScreen(
    appState: ApplicationState,
    diaryState: DiaryState,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }
    val searchState = remember { mutableStateOf(SearchState.MAIN_SEARCH) }

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

        val navController = rememberNavController()
        NavigationGraph(
            appState = appState,
            diaryState = diaryState,
            searchQuery = searchQuery,
            searchState = searchState,
            navController = navController
        )

    }
}
