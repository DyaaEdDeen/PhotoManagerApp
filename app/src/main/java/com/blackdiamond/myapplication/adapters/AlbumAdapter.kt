package com.blackdiamond.myapplication.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.blackdiamond.myapplication.R
import com.blackdiamond.myapplication.dataClasses.Album

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
        albumCover.setImageURI(Uri.parse(curAlbum.imagePaths[0]))
        albumCover.setOnClickListener {
            Toast.makeText(holder.itemView.context,"u clicked on ${curAlbum.folderName}",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}