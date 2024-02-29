package com.android.myfooddiarybookaos.model.image

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image(
    @SerializedName("imageId") val imageId: Int,
    @SerializedName("bytes") val bytes: String
) : Serializable
