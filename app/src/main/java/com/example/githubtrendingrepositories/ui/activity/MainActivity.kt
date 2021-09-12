package com.example.githubtrendingrepositories.ui.activity

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtrendingrepositories.R
import com.example.githubtrendingrepositories.data.remote.api.GitHubTrendingAPI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiObj = GitHubTrendingAPI()
        val viewTransformation = ViewTransformation()

        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val noInternet = findViewById<LinearLayout>(R.id.no_internet)
        val recyclerView = findViewById<RecyclerView>(R.id.listView)

        apiObj.getData(this, this, this,recyclerView, progressBar, noInternet)

        val retryButton = findViewById<Button>(R.id.retry_btn)

        retryButton.setOnClickListener {
            viewTransformation.showProgressBar(progressBar, noInternet)

            apiObj.getData(this, this, this,recyclerView, progressBar, noInternet)
        }
    }
}

