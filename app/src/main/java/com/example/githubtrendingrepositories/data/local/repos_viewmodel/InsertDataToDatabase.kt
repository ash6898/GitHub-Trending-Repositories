package com.example.githubtrendingrepositories.data.local.repos_viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity
import com.example.githubtrendingrepositories.ui.adapter.RecyclerViewAdapter
import com.example.githubtrendingrepositories.ui.viewmodel.ItemsViewModel

class InsertDataToDatabase {

    private lateinit var mUserViewModel: ReposViewModel
    private var count = 0

    fun insertDataToDatabase(context: Context, owner: ViewModelStoreOwner, reposEntity: ReposEntity) {

            // Add Data to Database
            mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
            mUserViewModel.addRepos(reposEntity)
            count += 1
            //Toast.makeText(context, count.toString(), Toast.LENGTH_LONG).show()
            // Navigate Back
    }

    fun showData(lifecycleOwner: LifecycleOwner, owner: ViewModelStoreOwner, context: Context, recyclerView: RecyclerView) {
        // UserViewModel
        mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
        mUserViewModel.readAllData.observe(lifecycleOwner, Observer { user ->
            //adapter.setData(user)
            val adapter = RecyclerViewAdapter(context, user)
            recyclerView.adapter = adapter
        })

    }

    fun

}