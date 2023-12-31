package com.android.myfooddiarybookaos.search.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.myfooddiarybookaos.Layout.NotDataView
import com.android.myfooddiarybookaos.model.search.SearchCategory
import com.android.myfooddiarybookaos.search.SearchViewModel
import com.android.myfooddiarybookaos.search.item.ItemSearchCategory

@Composable
fun PagingCategoryComponent(
    searchSelect: (SearchCategory) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val pagingItems = viewModel.pagingCategoryList.collectAsLazyPagingItems()


    if (pagingItems.itemCount == 0){
        NotDataView()
    }else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(28.dp),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 100.dp, top = 26.dp)
        ){
            items(pagingItems.itemSnapshotList){
                it?.let {
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

}