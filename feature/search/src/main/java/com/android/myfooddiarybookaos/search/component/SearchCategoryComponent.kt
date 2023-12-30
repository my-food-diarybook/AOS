package com.android.myfooddiarybookaos.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.model.search.SearchCategory
import com.android.myfooddiarybookaos.search.SearchViewModel
import com.android.myfooddiarybookaos.search.item.ItemSearchCategory

@Composable
fun SearchCategoryComponent(
    searchSelect: (SearchCategory) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val searchItems = viewModel.searchCategoryList.collectAsState()

    if (searchItems.value.isEmpty()){
        NoSearchDataComponent()
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ){
            items(searchItems.value){
                ItemSearchCategory(
                    searchCategory = it,
                    select = {
                        searchSelect(it)
                    }
                )
            }
        }
    }
}