package com.android.myfooddiarybookaos.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.myfooddiarybookaos.data.dataSearch.repository.SearchRepository
import com.android.myfooddiarybookaos.data.state.LoadState
import com.android.myfooddiarybookaos.model.search.SearchCategory
import com.android.myfooddiarybookaos.model.search.SearchDiary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private var _state : MutableStateFlow<LoadState> = MutableStateFlow(LoadState.Init)
    val state: StateFlow<LoadState> = _state.asStateFlow()

    private val _pagingCategoryList =
        MutableStateFlow<PagingData<SearchCategory>>(PagingData.empty())
    val pagingCategoryList: StateFlow<PagingData<SearchCategory>> =
        _pagingCategoryList.asStateFlow()

    private val _pagingDiaryList = MutableStateFlow<PagingData<SearchDiary>>(PagingData.empty())
    val pagingDiaryList: StateFlow<PagingData<SearchDiary>> =
        _pagingDiaryList.asStateFlow()

    private val _searchCategoryList = MutableStateFlow<List<SearchCategory>>(emptyList())
    val searchCategoryList: StateFlow<List<SearchCategory>> =
        _searchCategoryList.asStateFlow()

    fun getPagingCategories() = viewModelScope.launch {
        searchRepository.getMoreSearchData().collectLatest {
            _pagingCategoryList.value = it
        }
    }

    fun getPagingDiaries(
        categoryName: String,
        categoryType: String
    ) = viewModelScope.launch {
        runCatching {
            _state.emit(LoadState.Loading)
            searchRepository.getSearchDiary(
                categoryName, categoryType
            )
                .cachedIn(viewModelScope)
                .collectLatest {
                    _pagingDiaryList.value = it
                }
        }
            .onSuccess {
                _state.emit(LoadState.Init)
            }
            .onFailure {
                _state.emit(LoadState.Fail)
            }
    }

    fun getSearchData(
        searchQuery: String
    ) = viewModelScope.launch {
        runCatching {
            _state.emit(LoadState.Loading)
            searchRepository.getCurrentSearch(searchQuery)
                .collectLatest {
                    _searchCategoryList.value = it
                }
        }
            .onSuccess {
                _state.emit(LoadState.Init)
            }
            .onFailure {
                _state.emit(LoadState.Fail)
            }
    }
}
