package com.example.githubtrendingrepositories.data.remote.api

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity
import com.example.githubtrendingrepositories.data.local.repos_viewmodel.InsertDataToDatabase
import com.example.githubtrendingrepositories.data.local.repos_viewmodel.ReposViewModel
import com.example.githubtrendingrepositories.ui.adapter.RecyclerViewAdapter
import com.example.githubtrendingrepositories.ui.viewmodel.ItemsViewModel
import com.example.githubtrendingrepositories.ui.activity.ViewTransformation

class GitHubTrendingAPI {

    private lateinit var mUserViewModel: ReposViewModel

    val db = InsertDataToDatabase()

    fun getData(lifecycleOwner: LifecycleOwner,
                owner: ViewModelStoreOwner,
                context: Context,
                recyclerView: RecyclerView,
                progressBar: ProgressBar,
                noInternet: LinearLayout) {

        val apiURL = "https://gh-trending-api.herokuapp.com/repositories"

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)

        val data = ArrayList<ReposEntity>()

        val viewTransformation = ViewTransformation()

        val insertData = InsertDataToDatabase()

        // this creates a vertical layout Manager
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Request a string response from the provided URL.
        val stringRequest = JsonArrayRequest(Request.Method.GET, apiURL, null, Response.Listener
        { response ->

            db.

            // This loop will create 20 Views containing
            for (i in 0 until response.length()) {
                val jsonObject = response.getJSONObject(i)
                val username = jsonObject.get("username").toString()
                val repositoryName = jsonObject.get("repositoryName").toString()
                val description = jsonObject.get("description").toString()
                val language = jsonObject.get("language").toString()
                val url = jsonObject.get("url").toString()
                val languageColor = jsonObject.get("languageColor").toString()
                val totalStars = jsonObject.get("totalStars").toString()
                val starsSince = jsonObject.get("starsSince").toString()
                val forks = jsonObject.get("forks").toString()
                val rgbColor = Color.RED

                val repos = ReposEntity(
                    0,
                    repositoryName,
                    username,
                    description,
                    language,
                    url,
                    languageColor,
                    totalStars,
                    starsSince,
                    forks,
                    rgbColor.toString()
                )

                data.add(repos)

                db.insertDataToDatabase(context, owner, repos)

                db.showData(lifecycleOwner, owner, context, recyclerView)

                initializeRecyclerViewAdapter(context, data, recyclerView)

                viewTransformation.showRecyclerView(progressBar,recyclerView)
            }
        },
            {
                viewTransformation.showNoInternet(progressBar,noInternet)
                viewTransformation.showRecyclerView(progressBar,recyclerView)
                insertData.showData(lifecycleOwner, owner, context, recyclerView)
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    fun initializeRecyclerViewAdapter(context: Context, data: ArrayList<ReposEntity>, recyclerView: RecyclerView){
        // This will pass the ArrayList to our Adapter
        //val adapter = RecyclerViewAdapter(context, data)

        // Setting the Adapter with the recyclerview
        //recyclerView.adapter = adapter


    }



}