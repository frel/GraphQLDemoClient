package com.subgarden.democlient.ui

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.subgarden.democlient.R
import com.subgarden.democlient.data.AudioItem

class AudioViewHolder(
        itemView: View)
    : ItemViewHolder<AudioItem>(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.image)!!
    private val title = itemView.findViewById<TextView>(R.id.title)!!

    override fun bindTo(item: AudioItem) {
        title.text = item.title
        imageView.setBackgroundColor(Color.BLUE)
    }
}
