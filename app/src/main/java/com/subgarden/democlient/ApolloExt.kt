package com.subgarden.democlient

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Throws(ApolloException::class)
suspend fun <T> ApolloCall<T>.execute() = suspendCoroutine<Response<T>> { cont ->
    enqueue(object: ApolloCall.Callback<T>() {
        override fun onResponse(response: Response<T>) {
            cont.resume(response)
        }

        override fun onFailure(e: ApolloException) {
            cont.resumeWithException(e)
        }
    })
}
