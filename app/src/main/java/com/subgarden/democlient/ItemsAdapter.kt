package com.subgarden.democlient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager


class ItemsAdapter(
        private val imageRequestManager: RequestManager)
    : ListAdapter<Item, ItemViewHolder<Item>>(object : DiffUtil.ItemCallback<Item>() {

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return true
    }

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.uuid == newItem.uuid
    }

}){

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is WallpaperItem -> R.layout.image_item
            is AudioItem     -> R.layout.audio_item
            else             -> TODO("Unsupported item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<Item> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.image_item -> WallpaperViewHolder(view, imageRequestManager) as ItemViewHolder<Item>
            R.layout.audio_item -> AudioViewHolder(view) as ItemViewHolder<Item>
            else                -> TODO("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder<Item>, position: Int) {
        holder.bindTo(getItem(position))
    }
}
