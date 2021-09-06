package com.example.githubtrendingrepositories.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtrendingrepositories.R

import com.example.githubtrendingrepositories.data.remote.api.GitHubTrendingAPI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val API_obj = GitHubTrendingAPI()
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val noInternet = findViewById<LinearLayout>(R.id.no_internet)

        val recyclerView = findViewById<RecyclerView>(R.id.listView)

        API_obj.getData(this, recyclerView, progressBar, noInternet)

        val retry_btn = findViewById<Button>(R.id.retry_btn)

        retry_btn.setOnClickListener {
            noInternet.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            API_obj.getData(this, recyclerView, progressBar, noInternet)
        }
    }

    }

