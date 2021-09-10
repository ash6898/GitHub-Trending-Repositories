package com.example.githubtrendingrepositories.ui.activity

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtrendingrepositories.R
import com.example.githubtrendingrepositories.data.local.repos_viewmodel.InsertDataToDatabase
import com.example.githubtrendingrepositories.data.local.repos_viewmodel.ReposViewModel

import com.example.githubtrendingrepositories.data.remote.api.GitHubTrendingAPI

class MainActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiObj = GitHubTrendingAPI()
        val viewTransformation = ViewTransformation()

        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val noInternet = findViewById<LinearLayout>(R.id.no_internet)
        val recyclerView = findViewById<RecyclerView>(R.id.listView)

        apiObj.getData(this, this, recyclerView, progressBar, noInternet)

        val retryButton = findViewById<Button>(R.id.retry_btn)

        retryButton.setOnClickListener {
            viewTransformation.showProgressBar(progressBar, noInternet)

            apiObj.getData(this, this, recyclerView, progressBar, noInternet)
        }

        mUserViewModel = ViewModelProvider(this).get(ReposViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer { user ->
            adapter.setData(user)
        })

    }
}

