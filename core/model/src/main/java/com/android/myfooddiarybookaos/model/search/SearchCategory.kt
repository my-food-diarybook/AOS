package com.android.myfooddiarybookaos.model.search

import java.io.Serializable

data class SearchCategory(
    val categoryName: String,
    val categoryType: String,
    val count: Int,
    val diaryList: List<SearchDiary>
) : Serializable
