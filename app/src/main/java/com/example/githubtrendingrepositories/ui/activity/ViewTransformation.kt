package com.example.githubtrendingrepositories.ui.activity

import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class ViewTransformation {


    // This function hides progress bar and displays RecyclerView
    fun showRecyclerView(
        progressBar: ProgressBar,
        recyclerView: RecyclerView,
        swipeRefreshLayout: SwipeRefreshLayout,
        noInternet: LinearLayout
    ) {
        progressBar.visibility = View.GONE
        noInternet.visibility = View.GONE
        swipeRefreshLayout.visibility = View.VISIBLE
        recyclerView.visibility = View.VISIBLE

        Log.d("showw", "called in viewtrans")
    }

    // This function RecyclerView, ProgressBar and displays noInternet Layout
    fun showNoInternet(
        progressBar: ProgressBar,
        swipeRefreshLayout: SwipeRefreshLayout,
        noInternet: LinearLayout,
        recyclerView: RecyclerView
    ) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        swipeRefreshLayout.visibility = View.GONE
        noInternet.visibility = View.VISIBLE
        Log.d("noInternettt", "called in viewtrans")
    }

    // This function hides RecyclerView, NoIntenet Layout and shows ProgressBar
    fun showProgressBar(
        progressBar: ProgressBar,
        swipeRefreshLayout: SwipeRefreshLayout,
        noInternet: LinearLayout,
        recyclerView: RecyclerView
    ) {
        swipeRefreshLayout.visibility = View.GONE
        recyclerView.visibility = View.GONE
        noInternet.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

}