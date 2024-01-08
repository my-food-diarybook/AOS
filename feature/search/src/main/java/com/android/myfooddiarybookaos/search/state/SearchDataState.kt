package com.android.myfooddiarybookaos.search.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Stable
class SearchDataState(
    val searchQuery: MutableState<TextFieldValue>,
    val searchState: MutableState<SearchState>,
    val queryChangeState: MutableState<Boolean>,
    val navController: NavHostController,
    val categoryName : MutableState<String>,
    val categoryType : MutableState<String>
) {
}