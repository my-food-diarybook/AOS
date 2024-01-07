package com.android.myfooddiarybookaos.search

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.search.component.SearchBox
import com.android.myfooddiarybookaos.search.navi.NavigationGraph
import com.android.myfooddiarybookaos.search.state.SearchDataState
import com.android.myfooddiarybookaos.search.state.SearchState

@Composable
fun SearchScreen(
    appState: ApplicationState,
    diaryState: DiaryState,
    searchDataState: SearchDataState,
) {


    if (searchDataState.searchQuery.value.text.isNotEmpty()) {
        searchDataState.searchState.value = SearchState.QUERY_SEARCH
    } else {
        searchDataState.searchState.value = SearchState.MAIN_SEARCH
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
                searchDataState.searchQuery,
                searchDataState.searchState,
                onQueryChange = {
                    searchDataState.queryChangeState.value = true
                },
                onBackStage = {
                    if (searchDataState.navController.currentDestination?.route == "categoryScreen") {
                        searchDataState.navController.popBackStack()
                    }
                    searchDataState.searchQuery.value = TextFieldValue("")
                }
            )
        }

        NavigationGraph(
            appState = appState,
            diaryState = diaryState,
            searchDataState = searchDataState,
        )

    }
}
