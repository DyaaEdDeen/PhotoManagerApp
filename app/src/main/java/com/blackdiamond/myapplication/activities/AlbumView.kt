package com.blackdiamond.myapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blackdiamond.myapplication.R
import com.blackdiamond.myapplication.adapters.ImageAdapter

class AlbumView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_view)


        var imgs = intent.getStringArrayListExtra("imgs")
        val albumName = intent.getStringExtra("albumName")
        if(albumName != null){
            supportActionBar?.title = albumName
        }
        if (imgs != null) {
            val imageAdapter = ImageAdapter(imgs)
            val rvAlbum = findViewById<RecyclerView>(R.id.rvAlbumView)
            rvAlbum.adapter = imageAdapter
            rvAlbum.layoutManager = GridLayoutManager(this, 3)
        }
    }
}