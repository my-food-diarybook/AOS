package com.android.myfooddiarybookaos.search.navi

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.model.search.SearchCategory
import com.android.myfooddiarybookaos.search.SearchViewModel
import com.android.myfooddiarybookaos.search.state.SearchDataState
import com.android.myfooddiarybookaos.search.state.SearchState
import com.android.myfooddiarybookaos.search.ui.CategoryScreen
import com.android.myfooddiarybookaos.search.ui.MainSearchScreen
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

@Composable
fun NavigationGraph(
    appState: ApplicationState,
    diaryState: DiaryState,
    searchDataState: SearchDataState
) {

    NavHost(
        navController = searchDataState.navController,
        startDestination = "mainSearchScreen"
    ) {

        composable("mainSearchScreen") {
            MainSearchScreen(
                searchState = searchDataState.searchState,
                queryChangeState = searchDataState.queryChangeState,
                searchQuery = searchDataState.searchQuery,
                select = { SearchCategory ->
                    searchDataState.categoryName.value = SearchCategory.categoryName
                    searchDataState.categoryType.value = SearchCategory.categoryType
                    searchDataState.searchQuery.value =
                        if (SearchCategory.categoryName == "") TextFieldValue(" ")
                        else TextFieldValue(SearchCategory.categoryName)
                    searchDataState.navController.navigate("categoryScreen")
                }
            )
        }

        composable("categoryScreen") {
            CategoryScreen(
                categoryName = searchDataState.categoryName,
                categoryType = searchDataState.categoryType,
                selectDiary = {
                    diaryState.setDiaryDetail(it)
                    appState.navController.navigate(ScreenRoot.DETAIL_DIARY)
                }
            )
        }
    }

}