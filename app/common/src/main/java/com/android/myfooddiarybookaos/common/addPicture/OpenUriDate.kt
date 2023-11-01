package com.android.myfooddiarybookaos.common.addPicture

import android.annotation.SuppressLint
import android.content.Context
import android.media.ExifInterface
import android.net.Uri
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun getUriDate(uri : Uri?, context : Context)  : LocalDateTime? {
    var exifDate  :LocalDateTime? = null
    if (uri != null) {
        context.contentResolver.openInputStream(uri)?.use { stream->
            val exif = ExifInterface(stream)
            val exifDateFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss")
            val exifDateString = exif.getAttribute(ExifInterface.TAG_DATETIME_ORIGINAL)
            exifDate = LocalDateTime.parse(exifDateString, exifDateFormatter)
        }
    }
    return exifDate

}

@SuppressLint("SimpleDateFormat")
fun makeUriDate() : Date?{
    val date = Date(System.currentTimeMillis())
    val dateFormat = SimpleDateFormat("yyyy:MM:dd HH:mm:ss")
    val simpleDate = dateFormat.format(date)
    return dateFormat.parse(simpleDate)

}