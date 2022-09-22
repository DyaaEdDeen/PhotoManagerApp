package com.blackdiamond.myapplication.activities

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.blackdiamond.myapplication.R
import com.blackdiamond.myapplication.dataClasses.Album

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("galleryApp","${GetIMageAlbums()}")
    }

    private fun GetIMageAlbums() : ArrayList<Album>{
        var albums : ArrayList<Album> = ArrayList()

        val imageProjection = arrayOf(
            MediaStore.MediaColumns.DATA
        )
        val imageSortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            imageProjection,
            null,
            null,
            imageSortOrder
        )
        val imgAlbums = mutableMapOf<String,ArrayList<String>>()

        if (cursor != null) {
            while (cursor.moveToNext()){
                val colData = cursor.getColumnIndex(MediaStore.MediaColumns.DATA)
                val img_path = cursor.getString(colData)
                var folder_name = img_path.split("/")[img_path.split("/").size-2]
                if(!(folder_name in imgAlbums)){
                    imgAlbums[folder_name] = ArrayList()
                }
                imgAlbums[folder_name]?.add(img_path)
            }
        }

//        Log.d("galleryApp","list : $imgAlbums")
        for (imgList in imgAlbums.keys){
            val imgs = imgAlbums[imgList]
            val album = imgs?.let { Album(imgList, it) }
            if (album != null) {
                albums.add(album)
            }
        }
        return albums
    }


}