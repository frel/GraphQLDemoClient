
package com.subgarden.democlient

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloException
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import java.net.SocketTimeoutException

class MainActivity : AppCompatActivity() {

    companion object {
        const val IP_ADDRESS = "10.42.11.106"
        const val PORT = "8080"
    }

    private val adapter by lazy { ItemsAdapter(Glide.with(this@MainActivity)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        val apolloClient = try {
            ApolloClient.builder()
                    .serverUrl("http://$IP_ADDRESS:$PORT/graphql")
                    .okHttpClient(OkHttpClient.Builder().build())
                    .build()
        } catch (e: SocketTimeoutException) {
            Toast.makeText(this, "Unable to connect to server", Toast.LENGTH_LONG).show()
            return
        }

        val repository = ItemsRepository(apolloClient)

        GlobalScope.launch {
            try {
                val items = repository.getAllItems()
                withContext(Main) {
                    adapter.submitList(items)
                    progressBar.visibility = View.GONE
                }
            } catch (e: ApolloException) {
                e.printStackTrace()
                withContext(Main) {
                    progressBar.visibility = View.GONE
                    errorText.text = e.message
                    errorText.visibility = View.VISIBLE
                }
            }
        }
    }

}
