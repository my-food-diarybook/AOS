package com.android.myfooddiarybookaos.data.path

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Base64
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source

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
fun getMultipartFromUri(
    context: Context,
    contentUri: Uri,
    isOneImage: Boolean
): MultipartBody.Part? {
    return context.contentResolver.query(
        contentUri,
        null, null, null
    )?.use {
        val name = if(isOneImage) "file" else "files"
        if (it.moveToNext()) {
            val displayName = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            val requestBody = object : RequestBody() {
                override fun contentType(): MediaType? {
                    return context.contentResolver.getType(contentUri)?.toMediaType()
                }

                override fun writeTo(sink: BufferedSink) {
                    sink.writeAll(context.contentResolver.openInputStream(contentUri)?.source()!!)
                }
            }
            it.close()
            MultipartBody.Part.createFormData(name, displayName, requestBody)
        } else {
            it.close()
            null
        }
    }
}

fun byteStringToBitmap(byteString: String): Bitmap {
    val data = Base64.decode(byteString, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(data, 0, data.size)
}
