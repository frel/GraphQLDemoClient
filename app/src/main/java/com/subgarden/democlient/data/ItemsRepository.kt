package com.subgarden.democlient.data

import com.apollographql.apollo3.ApolloClient
import com.subgarden.democlient.demo.ItemsQuery
import com.subgarden.democlient.demo.TopFiveItemsQuery
import com.subgarden.democlient.domain.toDomainItem

class ItemsRepository(private val apolloClient: ApolloClient) {

    suspend fun allItems(): List<Item> = apolloClient
        .query(ItemsQuery())
        .execute()
        .let { response ->
            response.data?.items?.filterNotNull()?.map { it.toDomainItem() } ?: emptyList()
        }

    suspend fun topFiveItems(): List<Item> = apolloClient
        .query(TopFiveItemsQuery())
        .execute()
        .let { response ->
            response.data?.items?.filterNotNull()?.map { it.toDomainItem() } ?: emptyList()
        }
}



