package com.subgarden.democlient

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.rxQuery
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers

class ItemsRepository(private val apolloClient: ApolloClient) {

    fun allItems(): Flowable<List<Item>> = apolloClient
        .rxQuery(ItemsQuery.builder().build())
        .toFlowable(BackpressureStrategy.LATEST)
        .map { response ->
            response.data()?.items?.map { it.toItem() } ?: emptyList()
        }
        .observeOn(AndroidSchedulers.mainThread())

}



