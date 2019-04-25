package com.subgarden.democlient

fun ItemsQuery.Item.toItem(): Item {
    return when (this) {
        is ItemsQuery.AsWallpaper -> {
            WallpaperItem(
                    uuid,
                    title,
                    ctype,
                    tags,
                    description,
                    subtitle,
                    creation_time,
                    owner_uuid,
                    imageUrl!!,
                    "",
                    0,
                    0
            )
        }
        is ItemsQuery.AsAudio -> {
            AudioItem(
                    uuid,
                    title,
                    ctype,
                    tags,
                    description,
                    subtitle,
                    creation_time,
                    owner_uuid,
                    streamUri,
                    0f,
                    null)
        }
        else -> TODO("Unsupported item type")
    }

}
