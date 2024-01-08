package com.android.myfooddiarybookaos.search.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberSearchDataState(
    searchQuery: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue("")) },
    searchState: MutableState<SearchState> = remember { mutableStateOf(SearchState.MAIN_SEARCH) },
    queryChangeState: MutableState<Boolean> = remember { mutableStateOf(false) },
    navController: NavHostController = rememberNavController(),
    categoryName: MutableState<String> = remember { mutableStateOf("") },
    categoryType: MutableState<String> = remember { mutableStateOf("") }
) = remember(
    searchQuery,
    searchState,
    queryChangeState,
    navController,
    categoryName,
    categoryType
) {
    SearchDataState(
        searchQuery,
        searchState,
        queryChangeState,
        navController,
        categoryName,
        categoryType
    )
}