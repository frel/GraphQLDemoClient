package com.subgarden.democlient.domain

import com.subgarden.democlient.data.AudioItem
import com.subgarden.democlient.data.Item
import com.subgarden.democlient.data.WallpaperItem
import com.subgarden.democlient.demo.ItemsQuery
import com.subgarden.democlient.demo.TopFiveItemsQuery

/*
* Example of a mapper from the GraphQL struct to the domain struct
*/
fun TopFiveItemsQuery.Item.toDomainItem(): Item {
    return when {
        onWallpaper != null -> {
            WallpaperItem(
                commonItem.uuid,
                commonItem.title,
                commonItem.ctype,
                commonItem.tags,
                commonItem.description,
                commonItem.subtitle,
                commonItem.creationTime,
                commonItem.ownerUuid,
                onWallpaper.imageUrl,
                "",
                0,
                0
            )
        }

        onAudio != null -> {
            AudioItem(
                commonItem.uuid,
                commonItem.title,
                commonItem.ctype,
                commonItem.tags,
                commonItem.description,
                commonItem.subtitle,
                commonItem.creationTime,
                commonItem.ownerUuid,
                onAudio.streamUri,
                0f,
                null
            )
        }

        else -> TODO("Unsupported item type $this")
    }
}

fun ItemsQuery.Item.toDomainItem(): Item {
    return when {
        onWallpaper != null -> {
            WallpaperItem(
                commonItem.uuid,
                commonItem.title,
                commonItem.ctype,
                commonItem.tags,
                commonItem.description,
                commonItem.subtitle,
                commonItem.creationTime,
                commonItem.ownerUuid,
                onWallpaper.imageUrl,
                "",
                0,
                0
            )
        }

        onAudio != null -> {
            AudioItem(
                commonItem.uuid,
                commonItem.title,
                commonItem.ctype,
                commonItem.tags,
                commonItem.description,
                commonItem.subtitle,
                commonItem.creationTime,
                commonItem.ownerUuid,
                onAudio.streamUri,
                0f,
                null
            )
        }

        else -> TODO("Unsupported item type $this")
    }

}
