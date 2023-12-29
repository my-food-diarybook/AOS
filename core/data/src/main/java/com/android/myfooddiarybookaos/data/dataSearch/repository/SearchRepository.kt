package com.android.myfooddiarybookaos.data.dataSearch.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.data.dataSearch.remote.SearchCategoryPagingSource
import com.android.myfooddiarybookaos.data.dataSearch.remote.SearchDataPagingSource
import com.android.myfooddiarybookaos.model.search.SearchCategory
import com.android.myfooddiarybookaos.model.search.SearchDiary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    networkManager: NetworkManager,
    private val context: Context
) {

    private val manager = networkManager.getSearchApiService()

    suspend fun getCurrentSearch(
        searchCond: String
    ): Flow<List<SearchCategory>> = flow {
        try {
            emit(manager.searchCondition(searchCond = searchCond))
        } catch (_: Exception) {
        }
    }

    suspend fun getMoreSearchData(

    ): Flow<PagingData<SearchCategory>> {
        return Pager(
            config = PagingConfig(pageSize = 4)
        ) {
            SearchCategoryPagingSource(manager)
        }.flow
    }

    suspend fun getSearchDiary(
        categoryName: String,
        categoryType: String,
    ): Flow<PagingData<SearchDiary>> {
        return Pager(
            config = PagingConfig(pageSize = 3)
        ){
            SearchDataPagingSource(
                categoryName,
                categoryType,
                manager
            )
        }.flow
    }


}