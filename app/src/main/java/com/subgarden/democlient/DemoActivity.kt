
package com.subgarden.democlient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import okhttp3.OkHttpClient
import java.net.SocketTimeoutException
import kotlin.coroutines.experimental.suspendCoroutine

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val apolloClient = try {
            ApolloClient.builder()
                    .serverUrl("http://192.168.0.14:8080/graphql")
                    .okHttpClient(OkHttpClient())
                    .build()
        } catch (e: SocketTimeoutException) {
            Toast.makeText(this, "Unable to connect to server", Toast.LENGTH_LONG).show()
            return
        }

        launch(CommonPool) {
            try {
                val response = apolloClient.query(GetItemsQuery.builder().count(9).build()).execute()
                val allItems = response.data()?.allItems ?: emptyList()
                withContext(UI) {
                    recyclerView.adapter = ImageAdapter(allItems, Glide.with(this@MainActivity))
                }
            } catch (e: ApolloException) {
                e.printStackTrace()
            }
        }
    }

}

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
