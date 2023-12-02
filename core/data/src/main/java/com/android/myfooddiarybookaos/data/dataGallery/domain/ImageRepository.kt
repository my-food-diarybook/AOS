package com.android.myfooddiarybookaos.data.dataGallery.domain

import com.android.myfooddiarybookaos.model.image.GalleryImage

interface ImageRepository {

    fun getAllPhotos(
        page: Int,
        loadSize: Int,
        currentLocation : String? = null,
    ): MutableList<GalleryImage>

    fun getFolderList() : ArrayList<String>
}