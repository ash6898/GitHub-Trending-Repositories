package com.example.githubtrendingrepositories.data.local.repos_viewmodel

import android.content.Context
import android.util.Log
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity
import com.example.githubtrendingrepositories.ui.activity.ViewTransformation
import com.example.githubtrendingrepositories.ui.adapter.RecyclerViewAdapter

class InsertDataToDatabase {

    private lateinit var mUserViewModel: ReposViewModel

    // This function adds repositories received from GithubApi Class
    fun insertDataToDatabase(owner: ViewModelStoreOwner, reposEntity: ReposEntity) {
        mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
        mUserViewModel.addRepos(reposEntity)
    }

    // This function sends repositories stored in database to RecyclerViewAdapter
    fun showData(
        lifecycleOwner: LifecycleOwner,
        owner: ViewModelStoreOwner,
        context: Context,
        recyclerView: RecyclerView,
        progressBar: ProgressBar,
        swipeRefreshLayout: SwipeRefreshLayout,
        noInternet: LinearLayout
    ) {

        val viewTransformation = ViewTransformation()

        mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
        mUserViewModel.readAllData.observe(lifecycleOwner, Observer { user ->

            // If database has some data, display that data on RecyclerView
            if (user.size > 0) {
                val adapter = RecyclerViewAdapter(context, user)
                recyclerView.adapter = adapter

                Log.d("showw", "show in db " + user.size.toString())

                // Shows RecyclerView
                viewTransformation.showRecyclerView(
                    progressBar,
                    recyclerView,
                    swipeRefreshLayout,
                    noInternet
                )

            }

            // If database has no data, display "NO INTERNET CONNECTION"
            else {
                // Shows NoInternet
                viewTransformation.showNoInternet(
                    progressBar,
                    swipeRefreshLayout,
                    noInternet,
                    recyclerView
                )
            }
        })
    }

    // This function deletes all data in database
    fun deleteData(owner: ViewModelStoreOwner) {
        mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
        mUserViewModel.deleteAllRepos()
    }

}