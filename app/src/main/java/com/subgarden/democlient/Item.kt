package com.subgarden.democlient

interface Item {
    val uuid: String
    val title: String
    val ctype: Int
    val tags: List<String>
    val description: String?
    val subtitle: String?
    val creation_time: String?
    val owner_uuid: String

    fun equals(other: Item) : Boolean {
        return uuid == other.uuid &&
                title == other.title &&
                ctype == other.ctype &&
                tags == other.tags &&
                description == other.description &&
                subtitle == other.subtitle &&
                creation_time == other.creation_time &&
                owner_uuid == other.owner_uuid
    }
}

data class WallpaperItem(
        override val uuid: String,
        override val title: String,
        override val ctype: Int,
        override val tags: List<String>,
        override val description: String?,
        override val subtitle: String?,
        override val creation_time: String?,
        override val owner_uuid: String,
        val imageUrl: String,
        val microThumb: String,
        val width: Int,
        val height: Int) : Item

data class AudioItem(
        override val uuid: String,
        override val title: String,
        override val ctype: Int,
        override val tags: List<String>,
        override val description: String?,
        override val subtitle: String?,
        override val creation_time: String?,
        override val owner_uuid: String,
        val streamUri: String?,
        val duration: Float?,
        val audioFlowerUrl: String?) : Item
