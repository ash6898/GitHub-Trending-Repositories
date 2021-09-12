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
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity
import com.example.githubtrendingrepositories.ui.activity.ViewTransformation
import com.example.githubtrendingrepositories.ui.adapter.RecyclerViewAdapter

class InsertDataToDatabase {

    private lateinit var mUserViewModel: ReposViewModel
    private var count = 0

    fun insertDataToDatabase(owner: ViewModelStoreOwner, reposEntity: ReposEntity) {
            mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
            mUserViewModel.addRepos(reposEntity)
            count += 1
    }

    fun showData(lifecycleOwner: LifecycleOwner,
                 owner: ViewModelStoreOwner,
                 context: Context,
                 recyclerView: RecyclerView,
                 progressBar: ProgressBar,
                 noInternet: LinearLayout) {
        mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
        mUserViewModel.readAllData.observe(lifecycleOwner, Observer { user ->
            if(user.size > 0) {
                val adapter = RecyclerViewAdapter(context, user)
                recyclerView.adapter = adapter

                Log.d("showw", "show in db " + user.size.toString())

                val viewTransformation = ViewTransformation()
                viewTransformation.showRecyclerView(progressBar,recyclerView)
            }
            else{
                val viewTransformation = ViewTransformation()
                viewTransformation.showNoInternet(progressBar, noInternet)
                Log.d("noInternettt","called in insertdb")
                Log.d("showw", "show no internet from insertdb")
            }

        })

    }

    fun deleteData(owner: ViewModelStoreOwner){
        mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
        mUserViewModel.deleteAllRepos()
    }
 
}