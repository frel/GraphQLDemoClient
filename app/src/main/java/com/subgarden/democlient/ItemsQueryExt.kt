package com.subgarden.democlient

import com.subgarden.democlient.demo.ItemsQuery

/*
* Example of a mapper from the GraphQL struct to the domain struct
*/
fun ItemsQuery.Item.toItem(): Item {
    return when {
        asWallpaper != null -> {
            WallpaperItem(
                    uuid,
                    title,
                    ctype,
                    tags,
                    description,
                    subtitle,
                    creationTime,
                    ownerUuid,
                    asWallpaper.imageUrl!!,
                    "",
                    0,
                    0
            )
        }
        asAudio != null -> {
            AudioItem(
                    uuid,
                    title,
                    ctype,
                    tags,
                    description,
                    subtitle,
                    creationTime,
                    ownerUuid,
                    asAudio.streamUri,
                    0f,
                    null)
        }
        else -> TODO("Unsupported item type")
    }

}
