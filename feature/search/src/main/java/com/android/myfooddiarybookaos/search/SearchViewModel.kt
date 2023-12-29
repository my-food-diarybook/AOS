package com.android.myfooddiarybookaos.search

import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.data.dataSearch.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
): ViewModel() {


}