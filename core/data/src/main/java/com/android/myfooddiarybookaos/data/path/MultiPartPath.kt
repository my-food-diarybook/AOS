package com.android.myfooddiarybookaos.data.path

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun makeMultiPartFromUri(
    context: Context, uri: Uri
): MultipartBody.Part {
    val path = getFilePath(context, uri)
    val file = File(path)
    val imRequestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())

    return MultipartBody.Part.createFormData(
        "multipartFile",
        file.name,
        imRequestBody
    )

}