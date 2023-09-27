package com.subgarden.democlient.data

interface Item {
    val uuid: String
    val title: String
    val ctype: Int
    val tags: List<String>
    val description: String?
    val subtitle: String?
    val creationTime: String?
    val ownerUuid: String

    fun equals(other: Item): Boolean {
        return uuid == other.uuid &&
            title == other.title &&
            ctype == other.ctype &&
            tags == other.tags &&
            description == other.description &&
            subtitle == other.subtitle &&
            creationTime == other.creationTime &&
            ownerUuid == other.ownerUuid
    }
}

data class WallpaperItem(
    override val uuid: String,
    override val title: String,
    override val ctype: Int,
    override val tags: List<String>,
    override val description: String?,
    override val subtitle: String?,
    override val creationTime: String?,
    override val ownerUuid: String,
    val imageUrl: String,
    val microThumb: String,
    val width: Int,
    val height: Int,
) : Item

data class AudioItem(
    override val uuid: String,
    override val title: String,
    override val ctype: Int,
    override val tags: List<String>,
    override val description: String?,
    override val subtitle: String?,
    override val creationTime: String?,
    override val ownerUuid: String,
    val streamUri: String?,
    val duration: Float?,
    val audioFlowerUrl: String?,
) : Item
