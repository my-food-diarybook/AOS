package com.android.myfooddiarybookaos.common.addPicture

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

@Composable
fun SelectPhotoFromAlbumLauncher(callback: (Uri?) -> Unit){
    val takePhotoFromAlbumLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            result.data?.data?.let {uri ->
                callback(uri)
            } ?: run {
                callback(null)
            }
        }else{
            callback(null)
        }
    }
    val takePhotoFromAlbumIntent = makeAlbumIntent()
    SideEffect {
        takePhotoFromAlbumLauncher.launch(takePhotoFromAlbumIntent)
    }
}

fun makeAlbumIntent() : Intent {
    return Intent(
        Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    ).apply {
        type = "image/*"
        action = Intent.ACTION_GET_CONTENT
        putExtra(
            Intent.EXTRA_MIME_TYPES,
            arrayOf("image/jpeg", "image/png", "image/bmp", "image/webp")
        )
        putExtra(Intent.EXTRA_ALLOW_MULTIPLE,false)
    }
}