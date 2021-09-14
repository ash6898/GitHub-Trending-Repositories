package com.example.githubtrendingrepositories.data.remote.api

import android.content.Context
import android.util.Log
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity
import com.example.githubtrendingrepositories.data.local.repos_viewmodel.InsertDataToDatabase
import com.example.githubtrendingrepositories.ui.activity.ViewTransformation
import com.example.githubtrendingrepositories.ui.adapter.RecyclerViewAdapter

class GithubApi {

    private val db = InsertDataToDatabase()

    // This function gets JSON data from API URL
    fun getData(
        lifecycleOwner: LifecycleOwner,
        owner: ViewModelStoreOwner,
        context: Context,
        recyclerView: RecyclerView,
        progressBar: ProgressBar,
        swipeRefreshLayout: SwipeRefreshLayout,
        noInternet: LinearLayout,
    ) {

        val apiURL = "https://gh-trending-api.herokuapp.com/repositories"

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)

        val data = ArrayList<ReposEntity>()

        val viewTransformation = ViewTransformation()

        // this creates a vertical layout Manager
        recyclerView.layoutManager = LinearLayoutManager(context)

        val stringRequest = JsonArrayRequest(
            Request.Method.GET,
            apiURL,
            null,
            Response.Listener
            { response ->

                db.deleteData(owner)

                // This loop will iterate through JSON Array
                for (i in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(i)
                    val rank = jsonObject.get("rank").toString().toInt()
                    val username = jsonObject.get("username").toString()
                    val repositoryName = jsonObject.get("repositoryName").toString()
                    val description = jsonObject.get("description").toString()
                    val language = jsonObject.get("language").toString()
                    val url = jsonObject.get("url").toString()
                    val languageColor = jsonObject.get("languageColor").toString()
                    val totalStars = jsonObject.get("totalStars").toString()
                    val forks = jsonObject.get("forks").toString()

                    val repos = ReposEntity(
                        rank,
                        repositoryName,
                        username,
                        description,
                        language,
                        url,
                        languageColor,
                        totalStars,
                        forks,
                    )

                    data.add(repos)

                    // Adding data to the database
                    db.insertDataToDatabase(owner, repos)

                    // Sets data to the RecyclerView Adapter
                    val adapter = RecyclerViewAdapter(context, data)
                    recyclerView.adapter = adapter

                    // hides progressbar and shows RecyclerView
                    viewTransformation.showRecyclerView(
                        progressBar,
                        recyclerView,
                        swipeRefreshLayout,
                        noInternet
                    )
                }
            },
            {
                // If unable to fetch from API this fun will check database any data and display it
                db.showData(
                    lifecycleOwner,
                    owner,
                    context,
                    recyclerView,
                    progressBar,
                    swipeRefreshLayout,
                    noInternet
                )
            }
        )

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }

}