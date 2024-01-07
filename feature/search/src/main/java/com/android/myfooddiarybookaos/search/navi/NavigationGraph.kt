package com.android.myfooddiarybookaos.search.navi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.model.search.SearchCategory
import com.android.myfooddiarybookaos.search.state.SearchState
import com.android.myfooddiarybookaos.search.ui.CategoryScreen
import com.android.myfooddiarybookaos.search.ui.MainSearchScreen
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

@Composable
fun NavigationGraph(
    appState: ApplicationState,
    diaryState: DiaryState,
    searchQuery: MutableState<TextFieldValue>,
    searchState: MutableState<SearchState>,
    navController: NavHostController
) {
    val categoryName = remember { mutableStateOf("") }
    val categoryType = remember { mutableStateOf("") }

    if (searchQuery.value.text.isNotEmpty()) {
        searchState.value = SearchState.QUERY_SEARCH
    }else {
        searchState.value = SearchState.MAIN_SEARCH
    }

    NavHost(
        navController = navController,
        startDestination = "mainSearchScreen"
    ){

        composable("mainSearchScreen"){
            MainSearchScreen(
                searchState = searchState,
                select = { SearchCategory ->
                categoryName.value = SearchCategory.categoryName
                categoryType.value = SearchCategory.categoryType
                searchQuery.value = TextFieldValue(SearchCategory.categoryName)
                navController.navigate("categoryScreen")
                }
            )
        }
        composable("categoryScreen"){
            CategoryScreen(
                categoryName,
                categoryType,
                selectDiary = {
                    diaryState.setDiaryDetail(it)
                    appState.navController.navigate(ScreenRoot.DETAIL_DIARY)
                }
            )
        }
    }

}