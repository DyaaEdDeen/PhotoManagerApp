package com.blackdiamond.myapplication.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.blackdiamond.myapplication.R
import com.blackdiamond.myapplication.activities.AlbumView
import com.blackdiamond.myapplication.dataClasses.Album
import com.squareup.picasso.Picasso
import java.io.File

class AlbumAdapter(private val albums: ArrayList<Album>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.album_view_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val curAlbum = albums[position]
        val albumTitle = holder.itemView.findViewById<TextView>(R.id.tvAlbumName)
        val albumCover = holder.itemView.findViewById<ImageView>(R.id.ivAlbumCover)
        albumTitle.text = curAlbum.folderName
        val img = File(curAlbum.imagePaths[0])
        Picasso.get().load(img).placeholder(R.drawable.image_place_holder).into(albumCover)
        albumCover.setOnClickListener {
            val intent = Intent(holder.itemView.context,AlbumView::class.java)
            intent.putExtra("imgs",curAlbum.imagePaths)
            intent.putExtra("albumName",curAlbum.folderName)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}