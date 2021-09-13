package com.example.githubtrendingrepositories.data.local.repos_viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
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
    private var count = 0
    val firstToast: Boolean = true

    fun insertDataToDatabase(owner: ViewModelStoreOwner, reposEntity: ReposEntity) {
        mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
        mUserViewModel.addRepos(reposEntity)
        count += 1
    }

    fun showData(
        lifecycleOwner: LifecycleOwner,
        owner: ViewModelStoreOwner,
        context: Context,
        recyclerView: RecyclerView,
        progressBar: ProgressBar,
        swipeRefreshLayout: SwipeRefreshLayout,
        noInternet: LinearLayout,
        fromSwipe: Boolean
    ) {
        val viewTransformation = ViewTransformation()

        mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
        mUserViewModel.readAllData.observe(lifecycleOwner, Observer { user ->
            if (user.size > 0) {
                val adapter = RecyclerViewAdapter(context, user)
                recyclerView.adapter = adapter

                Log.d("showw", "show in db " + user.size.toString())

                viewTransformation.showRecyclerView(
                    progressBar,
                    recyclerView,
                    swipeRefreshLayout,
                    noInternet
                )
                //if (fromSwipe) {
                    //Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show()
                //}
            } else {
                viewTransformation.showProgressBar(
                    progressBar,
                    swipeRefreshLayout,
                    noInternet,
                    recyclerView
                )
                viewTransformation.showNoInternet(
                    progressBar,
                    swipeRefreshLayout,
                    noInternet,
                    recyclerView
                )
                Log.d("noInternettt", "called in insertdb")
                Log.d("showw", "show no internet from insertdb")
            }

        })

    }

    fun deleteData(owner: ViewModelStoreOwner) {
        mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
        mUserViewModel.deleteAllRepos()
    }

}