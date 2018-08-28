package com.subgarden.democlient

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager

class ImageAdapter(
        val allItems: MutableList<GetItemsQuery.AllItem>,
        private val imageRequestManager: RequestManager)
    : RecyclerView.Adapter<ImageViewHolder>() {

    override fun getItemCount() = allItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view, imageRequestManager)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindTo(allItems[position])
    }

}


class ImageViewHolder(
        itemView: View,
        private val imageRequestManager: RequestManager)
    : RecyclerView.ViewHolder(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.image)!!
    private val title = itemView.findViewById<TextView>(R.id.title)!!

    fun bindTo(item: GetItemsQuery.AllItem) {
        title.text = item.name
        imageRequestManager
                .asBitmap()
                .load(item.thumbnail)
                .into(imageView)
    }
}

