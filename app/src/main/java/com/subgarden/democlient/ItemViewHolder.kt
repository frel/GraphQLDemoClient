package com.subgarden.democlient

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindTo(item: T)
}
