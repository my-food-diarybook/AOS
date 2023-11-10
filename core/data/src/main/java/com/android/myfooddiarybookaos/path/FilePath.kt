package com.android.myfooddiarybookaos.path

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Base64
import android.util.Log

//Uri를 String으로 전환
@SuppressLint("Recycle")
fun getImageFilePath(context: Context, contentUri: Uri): String {
    var columnIndex = 0
    val projection = arrayOf(MediaStore.Images.Media.DATA)

    val cursor = context.contentResolver.query(
        contentUri, projection,
        null, null, null
    )
    if (cursor!!.moveToFirst()) {
        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
    }
    return cursor.getString(columnIndex)
}

@SuppressLint("Recycle")
fun getVideoFilePath(context: Context, contentUri: Uri): String {
    var columnIndex = 0
    val projection = arrayOf(MediaStore.Video.Media.DATA)

    val cursor = context.contentResolver.query(
        contentUri, projection,
        null, null, null
    )
    if (cursor!!.moveToFirst()) {
        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
    }
    return cursor.getString(columnIndex)
}
// document (문서)에 등록 된 파일을 찾음
@SuppressLint("Recycle", "Range")
fun getFilePath(context: Context, contentUri: Uri): String{
    val cursor  = context.contentResolver.query(contentUri,
        null,null,null)

    cursor?.use {
        if (it.moveToFirst()){ // 첫 발견
            val displayName :String = it.getString(it
                .getColumnIndex(OpenableColumns.DISPLAY_NAME))
            val projection = arrayOf(MediaStore.Video.Media.DATA)
            val newCursor = context.contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                ,projection,null,null,null)
            newCursor?.use {it2->
                if (it2.moveToFirst()){
                    val data = it2.getString(it2.getColumnIndex(MediaStore.Video.Media.DATA))
                    if (data.contains(displayName)){
                        return data
                    }
                }
            }
        }
    }

    return "null"
}

fun byteStringToBitmap(byteString: String): Bitmap {
    val data = Base64.decode(byteString,Base64.DEFAULT)
    Log.d("data",data.toString())
    return BitmapFactory.decodeByteArray(data, 0, data.size)
}