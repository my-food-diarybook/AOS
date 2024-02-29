package com.android.myfooddiarybookaos.search.navi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.myfooddiarybookaos.data.state.ApplicationState
import com.android.myfooddiarybookaos.data.state.DiaryState
import com.android.myfooddiarybookaos.search.state.SearchDataState
import com.android.myfooddiarybookaos.search.ui.CategoryScreen
import com.android.myfooddiarybookaos.search.ui.MainSearchScreen
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot

@Composable
fun NavigationGraph(
    isUpdateView: MutableState<Boolean>,
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
                isUpdateView = isUpdateView,
                searchState = searchDataState.searchState,
                queryChangeState = searchDataState.queryChangeState,
                searchQuery = searchDataState.searchQuery,
                selectItem = {
                    diaryState.setDiaryDetail(it.diaryId)
                    appState.navController.navigate(ScreenRoot.DETAIL_DIARY)
//                    searchDataState.categoryName.value = searchCategory.categoryName
//                    searchDataState.categoryType.value = searchCategory.categoryType
//                    searchDataState.searchQuery.value =
//                        if (searchCategory.categoryName == "") TextFieldValue(" ")
//                        else TextFieldValue(searchCategory.categoryName)
//                    searchDataState.navController.navigate("categoryScreen")s
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
