package com.example.githubtrendingrepositories.ui.activity

import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

class ViewTransformation {

    fun showRecyclerView(progressBar: ProgressBar, recyclerView: RecyclerView){
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    fun showNoInternet(progressBar: ProgressBar, noInternet: LinearLayout){
        //progressBar.visibility = View.GONE
        //noInternet.visibility = View.VISIBLE
    }

    fun showProgressBar(progressBar: ProgressBar, noInternet: LinearLayout){
        //noInternet.visibility = View.GONE
        //progressBar.visibility = View.VISIBLE
    }

}