package com.subgarden.democlient

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.rxQuery
import com.subgarden.democlient.demo.ItemsQuery
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers

class ItemsRepository(private val apolloClient: ApolloClient) {

    fun allItems(): Flowable<List<Item>> = apolloClient
        .rxQuery(ItemsQuery())
        .toFlowable(BackpressureStrategy.LATEST)
        .map { response ->
            response.data()?.items?.filterNotNull()?.map { it.toItem() } ?: emptyList()
        }
        .observeOn(AndroidSchedulers.mainThread())

}



