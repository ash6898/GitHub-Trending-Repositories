package com.example.githubtrendingrepositories.data.local.repos_viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity

class InsertDataToDatabase {

    private lateinit var mUserViewModel: ReposViewModel
    private var count = 0

    fun insertDataToDatabase(context: Context, owner: ViewModelStoreOwner, reposEntity: ReposEntity) {

            // Add Data to Database
            mUserViewModel = ViewModelProvider(owner).get(ReposViewModel::class.java)
            mUserViewModel.addRepos(reposEntity)
            count += 1
            Toast.makeText(context, count.toString(), Toast.LENGTH_LONG).show()
            // Navigate Back
    }

}