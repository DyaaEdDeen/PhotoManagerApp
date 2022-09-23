package com.blackdiamond.myapplication.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.blackdiamond.myapplication.R

class ImageAdapter (private val imgs: ArrayList<String>):
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

        class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            return ImageViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.image_view_item,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            val curPhoto = imgs[position]
            val photo = holder.itemView.findViewById<ImageView>(R.id.ivImage)
            photo.setImageURI(Uri.parse(curPhoto))
            photo.setOnClickListener {
                Toast.makeText(holder.itemView.context,"u clicked on $curPhoto", Toast.LENGTH_SHORT).show()
            }
        }

        override fun getItemCount(): Int {
            return imgs.size
        }
}