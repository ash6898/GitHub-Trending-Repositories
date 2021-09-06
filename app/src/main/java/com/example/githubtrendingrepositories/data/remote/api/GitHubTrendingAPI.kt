package com.example.githubtrendingrepositories.data.remote.api

import android.content.Context
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.githubtrendingrepositories.ui.adapter.RecyclerViewAdapter
import com.example.githubtrendingrepositories.ui.viewmodel.ItemsViewModel
import com.example.githubtrendingrepositories.ui.activity.ViewTransformation

class GitHubTrendingAPI() {

    fun getData(context: Context, recyclerView: RecyclerView, progressBar: ProgressBar, noInternet: LinearLayout) {

        val url = "https://gh-trending-api.herokuapp.com/repositories"

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)

        val data = ArrayList<ItemsViewModel>()

        val viewTransformation = ViewTransformation()

        // this creates a vertical layout Manager
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Request a string response from the provided URL.
        val stringRequest = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener
        { response ->

            // This loop will create 20 Views containing
            for (i in 0 until response.length()) {
                val jsonObject = response.getJSONObject(i)
                val username = jsonObject.get("username").toString()
                val repositoryName = jsonObject.get("repositoryName").toString()
                val description = jsonObject.get("description").toString()
                val language = jsonObject.get("language").toString()
                data.add(ItemsViewModel(repositoryName, username, description, language))

                initializeRecyclerViewAdapter(data, recyclerView)

                viewTransformation.showRecyclerView(progressBar, recyclerView)
            }
        },
            {
                viewTransformation.showNoInternet(progressBar, noInternet)
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    fun initializeRecyclerViewAdapter(data: ArrayList<ItemsViewModel>, recyclerView: RecyclerView){
        // This will pass the ArrayList to our Adapter
        val adapter = RecyclerViewAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter
    }

}