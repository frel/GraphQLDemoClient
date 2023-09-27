package com.subgarden.democlient.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import com.subgarden.democlient.R
import com.subgarden.democlient.data.WallpaperItem


class WallpaperViewHolder(
        itemView: View,
        private val imageRequestManager: RequestManager)
    : ItemViewHolder<WallpaperItem>(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.image)!!
    private val title = itemView.findViewById<TextView>(R.id.title)!!

    override fun bindTo(item: WallpaperItem) {
        title.text = item.title
        imageRequestManager
                .asBitmap()
                .load(item.imageUrl)
                .into(imageView)
    }
}
