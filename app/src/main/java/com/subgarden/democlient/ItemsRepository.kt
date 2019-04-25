package com.subgarden.democlient

import com.apollographql.apollo.ApolloClient

class ItemsRepository(private val apolloClient: ApolloClient) {

    suspend fun getAllItems(): List<Item> {
        val response = apolloClient.query(ItemsQuery.builder().build()).execute()

        if (response.data()?.items == null) {
            return emptyList()
        }
        val allItems = response.data()?.items!!

        return allItems.map {
            it.toItem()
        }
    }
}



