package com.android.myfooddiarybookaos.home.viewModel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.data.dataHome.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class DiaryViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    fun makeNewDiary(
        createTime : String,
        cameraBitmap : Bitmap?,
        galleryList : List<Uri>?,
    ){
        val multiPartList = if (cameraBitmap!=null){
            homeRepository.makePartListFromBitmap(cameraBitmap)
        } else{
            homeRepository.makePartListFromUri(galleryList!!)
        }
        homeRepository.postNewDiary(
            createTime,
            null,null,null,
            multiPartList
        )
    }


}