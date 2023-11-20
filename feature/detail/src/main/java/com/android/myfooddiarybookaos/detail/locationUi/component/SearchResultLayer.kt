package com.android.myfooddiarybookaos.detail.locationUi.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.myfooddiarybookaos.detail.locationUi.item.SearchResultItem
import com.android.myfooddiarybookaos.detail.viewModel.DetailViewModel
import com.android.myfooddiarybookaos.model.map.Place

@Composable
fun SearchResultLayer(
    userInput: String,
    searchResult: State<List<Place>?>,
) {
    LazyColumn(
        state = rememberLazyListState()
    ){
        searchResult.value?.let { results ->
            items(results){ place ->
                SearchResultItem(
                    query = userInput,
                    place = place,
                    onSelected = {
                        // 선택
                    }
                )
            }
        }
    }
}