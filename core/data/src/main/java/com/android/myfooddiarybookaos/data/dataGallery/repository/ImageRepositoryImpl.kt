package com.android.myfooddiarybookaos.data.dataGallery.repository

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.core.os.bundleOf
import com.android.myfooddiarybookaos.data.dataGallery.domain.ImageRepository
import com.android.myfooddiarybookaos.model.image.GalleryImage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): ImageRepository {


    // 버전 대응 -> API 30 이상이므로 필요 없음
    private val uriExternal : Uri by lazy {
        MediaStore.Images.Media.getContentUri(
            MediaStore.VOLUME_EXTERNAL
        )
    }

    // 데이터 형식
    private val projection = arrayOf(
        MediaStore.Images.ImageColumns.DATA,
        MediaStore.Images.ImageColumns.DISPLAY_NAME,
        MediaStore.Images.ImageColumns.DATE_TAKEN,
        MediaStore.Images.ImageColumns._ID
    )

    // content resolver 활용
    private val contentResolver by lazy { context.contentResolver }

    override fun getAllPhotos(page: Int, loadSize: Int): MutableList<GalleryImage> {
        val galleryImageList = mutableListOf<GalleryImage>()

        val offset = (page - 1) * loadSize // 오프셋 위치
        val query = getQuery(offset, loadSize)

        query?.use { cursor ->
            while (cursor.moveToNext()){
                val id =
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID))
                val name =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME))
                val filePath =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA))
                val date =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN))
                val contentUri = ContentUris.withAppendedId(uriExternal, id)
                val image = GalleryImage(
                    id = id,
                    filePath = filePath,
                    uri = contentUri,
                    name = name,
                    date = date ?: "",
                    size = 0,
                )
                galleryImageList.add(image)
            }
        }
        return galleryImageList
    }

    @SuppressLint("Recycle")
    private fun getQuery(
        offset: Int,
        limit: Int,
    ) : Cursor?{
        val bundle = bundleOf(
            ContentResolver.QUERY_ARG_OFFSET to offset,
            ContentResolver.QUERY_ARG_LIMIT to limit,
            ContentResolver.QUERY_ARG_SORT_COLUMNS to arrayOf(MediaStore.Files.FileColumns.DATE_MODIFIED),
            ContentResolver.QUERY_ARG_SORT_DIRECTION to ContentResolver.QUERY_SORT_DIRECTION_DESCENDING,
            ContentResolver.QUERY_ARG_SQL_SELECTION to null,
            ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS to null,
        )
        return contentResolver.query(uriExternal, projection, bundle, null)
    }

}