package com.android.myfooddiarybookaos.home.viewModel

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.android.myfooddiarybookaos.data.dataHome.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    fun makeNewDiary(
        createTime : String,
        files : List<MultipartBody.Part>,
        diaryState : (Boolean) -> Unit
    ){
        homeRepository.postNewDiary(
            createTime,
            null,null,null,
            files,
            isSuccess = {
                diaryState(it)
            }
        )
    }

    fun getMultiPartFromBitmap(
        cameraBitmap : Bitmap
    ) : List<MultipartBody.Part> {
        return homeRepository.makePartListFromBitmap(cameraBitmap)
    }

    fun getMultiPartFromUri(
        uriList : List<Uri>
    ) : List<MultipartBody.Part>{
        return homeRepository.makePartListFromUri(uriList)
    }


}