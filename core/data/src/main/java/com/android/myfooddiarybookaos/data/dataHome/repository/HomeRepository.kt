package com.android.myfooddiarybookaos.data.dataHome.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.file.getFilePath
import com.android.myfooddiarybookaos.model.diary.PlaceInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.BufferedSink
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class HomeRepository(
    private val networkManager: NetworkManager,
    @ApplicationContext private val context: Context
) {
    private val manager = networkManager.getDiaryApiService()

    fun postNewDiary(
        createTime : String,
        place: String?,
        longitude: Double?,
        latitude: Double?,
        fileList : List<MultipartBody.Part>
    ){
        try {
            val placeInfo = PlaceInfo(place, longitude, latitude)

            manager.newDiary(
                createTime,
                placeInfo,
                fileList
            ).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {

                }

                override fun onFailure(call: Call<String>, t: Throwable) {}

            })
        } catch (e: Exception) { }
    }

    fun makePartListFromUri(
        imageUriList : List<Uri>
    ) : List<MultipartBody.Part> {
        val files = ArrayList<MultipartBody.Part>()
        for (image in imageUriList){
            files.add(makeMultiPartFromUri(image))
        }
        return files
    }

    fun makePartListFromBitmap(
        imageBitmap: Bitmap
    ) : List<MultipartBody.Part> {
        return listOf(makeMultiPartFromBitmap(imageBitmap))
    }

    private fun makeMultiPartFromUri(uri: Uri): MultipartBody.Part {
        val path = getFilePath(context, uri)
        val file = File(path)
        val imRequestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData(
            "multipartFile",
            file.name,
            imRequestBody
        )

    }

    private fun makeMultiPartFromBitmap(bitmap: Bitmap) : MultipartBody.Part {
        val bitmapRequestBody = BitmapRequestBody(bitmap)
        val bitmapCode: String = bitmap.toString().split("@").last()
        return MultipartBody.Part.createFormData(
            "multipartFile",
            bitmapCode,
            bitmapRequestBody
        )
    }

    //비트맵 -> requsetbody 변환
    inner class BitmapRequestBody(private  val bitmap : Bitmap) : RequestBody(){
        override fun contentType(): MediaType = "image/jpeg".toMediaType()
        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG,99,sink.outputStream())
        }
    }
}