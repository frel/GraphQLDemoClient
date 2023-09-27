package com.subgarden.democlient.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.subgarden.democlient.R
import com.subgarden.democlient.data.AudioItem
import com.subgarden.democlient.data.Item
import com.subgarden.democlient.data.WallpaperItem
import com.subgarden.democlient.ui.AudioViewHolder
import com.subgarden.democlient.ui.ItemViewHolder
import com.subgarden.democlient.ui.WallpaperViewHolder


class ItemsAdapter(
    private val imageRequestManager: RequestManager
) : ListAdapter<Item, ItemViewHolder<Item>>(object : DiffUtil.ItemCallback<Item>() {

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return true
    }

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.uuid == newItem.uuid
    }

}) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is WallpaperItem -> R.layout.image_item
            is AudioItem -> R.layout.audio_item
            else -> TODO("Unsupported item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<Item> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        @Suppress("UNCHECKED_CAST")
        return when (viewType) {
            R.layout.image_item -> WallpaperViewHolder(view, imageRequestManager)
            R.layout.audio_item -> AudioViewHolder(view)
            else -> TODO("Unsupported view type")
        } as ItemViewHolder<Item>
    }

    override fun onBindViewHolder(holder: ItemViewHolder<Item>, position: Int) {
        holder.bindTo(getItem(position))
    }
}
