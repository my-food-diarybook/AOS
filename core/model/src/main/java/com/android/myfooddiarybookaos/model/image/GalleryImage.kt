package com.android.myfooddiarybookaos.model.image

import android.net.Uri

data class GalleryImage(
    val id: Long,
    val filePath: String,
    val uri: Uri,
    val name: String,
    val date: String,
    val size: Int,
    var isSelected: Boolean = false,
)
