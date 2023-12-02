package com.android.myfooddiarybookaos.data.dataHome.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.android.myfooddiarybookaos.api.NetworkManager
import com.android.myfooddiarybookaos.data.path.getMultipartFromUri
import com.android.myfooddiarybookaos.model.diary.PlaceInfo
import com.android.myfooddiarybookaos.model.diary.PlaceInfoBody
import com.android.myfooddiarybookaos.data.path.toApplicationRequestBody
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePostRepository(
    private val networkManager: NetworkManager,
    @ApplicationContext private val context: Context
) {
    private val manager = networkManager.getDiaryMultiPartApiService()

    fun postNewDiary(
        createTime : String,
        place: String?,
        longitude: Double?,
        latitude: Double?,
        fileList : List<MultipartBody.Part>,
        isSuccess : (Boolean) -> Unit
    ){
        try {
            val placeRequest = PlaceInfoBody(
                placeInfo = PlaceInfo(place,longitude,latitude)
            )
            val json = Gson().toJson(placeRequest)
            val requestBody = json.toApplicationRequestBody()
            manager.newDiary(
                createTime,
                requestBody,
                fileList
            ).enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) isSuccess(true)
                    else isSuccess(false)
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    isSuccess(false)
                }

            })
        } catch (e: Exception) {
            isSuccess(false)
        }
    }


    fun makePartListFromUri(
        imageUriList : List<Uri>,
        isOneImage: Boolean
    ) : List<MultipartBody.Part> {
        val files = ArrayList<MultipartBody.Part>()
        for (image in imageUriList){
            getMultipartFromUri(context,image,isOneImage)?.let { files.add(it) }
        }
        return files
    }

    // 비트맵 형식 변환
    fun makePartListFromBitmap(
        imageBitmap: Bitmap
    ) : List<MultipartBody.Part> {
        return listOf(makeMultiPartFromBitmap(imageBitmap))
    }


    private fun makeMultiPartFromBitmap(bitmap: Bitmap) : MultipartBody.Part {
        val bitmapRequestBody = BitmapRequestBody(bitmap)
        val bitmapCode: String = bitmap.toString().split("@").last()
        return MultipartBody.Part.createFormData(
            "files",
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