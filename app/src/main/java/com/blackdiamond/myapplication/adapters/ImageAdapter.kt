package com.blackdiamond.myapplication.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.blackdiamond.myapplication.R
import com.blackdiamond.myapplication.activities.ImageViewActivity
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

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
            val img = File(curPhoto)
            Picasso.get().load(img)
                .placeholder(R.drawable.image_place_holder)
                .fit()
                .centerCrop()
                .transform(
                    RoundedCornersTransformation(16, 0,
                    RoundedCornersTransformation.CornerType.ALL)
                )
                .into(photo)
            photo.setOnClickListener {
                val intent = Intent(holder.itemView.context, ImageViewActivity::class.java)
                intent.putExtra("photo",curPhoto)
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return imgs.size
        }
}