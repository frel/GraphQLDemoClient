package com.subgarden.democlient.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.bumptech.glide.Glide
import com.subgarden.democlient.data.ItemsRepository
import com.subgarden.democlient.databinding.ActivityMainBinding
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    companion object {
        const val IP_ADDRESS = "10.42.13.81"
        const val PORT = "8080"
    }

    private val adapter by lazy { ItemsAdapter(Glide.with(this@MainActivity)) }
    private val apolloClient = ApolloClient.Builder()
        .serverUrl("http://$IP_ADDRESS:$PORT/graphql")
        .okHttpClient(OkHttpClient.Builder().build())
        .build()
    private val repository = ItemsRepository(apolloClient)


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            try {
                val items = repository.topFiveItems()
//                val items = repository.allItems()
                adapter.submitList(items)
                binding.progressBar.visibility = GONE
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                binding.progressBar.visibility = GONE
                binding.errorText.text = e.message
                binding.errorText.visibility = VISIBLE
            }
        }
    }
}

