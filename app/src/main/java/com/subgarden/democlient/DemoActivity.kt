package com.subgarden.democlient

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.ApolloClient
import com.bumptech.glide.Glide
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import java.net.SocketTimeoutException

class MainActivity : AppCompatActivity() {

    companion object {
        const val IP_ADDRESS = "192.168.0.10"
        const val PORT = "8080"
    }

    private val adapter by lazy { ItemsAdapter(Glide.with(this@MainActivity)) }

    private val disposable = CompositeDisposable()

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

        repository
            .allItems()
            .subscribe({
                adapter.submitList(it)
                progressBar.visibility = View.GONE
            }, {
                progressBar.visibility = View.GONE
                errorText.text = it.message
                errorText.visibility = View.VISIBLE
            })
            .let(disposable::add)

    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

}

