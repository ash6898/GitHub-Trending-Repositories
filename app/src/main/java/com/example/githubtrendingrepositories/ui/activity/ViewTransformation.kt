package com.example.githubtrendingrepositories.ui.activity

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class ViewTransformation {

    fun showRecyclerView(progressBar: ProgressBar, recyclerView: RecyclerView, swipeRefreshLayout: SwipeRefreshLayout){
        progressBar.visibility = View.GONE
        swipeRefreshLayout.visibility = View.VISIBLE
        recyclerView.visibility = View.VISIBLE

        Log.d("showw", "called in viewtrans")
    }

    fun showNoInternet(packageContext: Context){
        //val intent = Intent(packageContext, NoInternet::class.java)
        //startActivity(packageContext, intent, null)
        Log.d("noInternettt","called in viewtrans")
    }

    fun showProgressBar(progressBar: ProgressBar, swipeRefreshLayout: SwipeRefreshLayout){
        swipeRefreshLayout.visibility = View.GONE
        //noInternet.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

}