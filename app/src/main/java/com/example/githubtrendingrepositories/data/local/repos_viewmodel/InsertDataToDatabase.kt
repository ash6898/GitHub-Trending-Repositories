package com.example.githubtrendingrepositories.data.local.repos_viewmodel

import android.content.Context
import android.widget.Toast
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity

class InsertDataToDatabase {

    private lateinit var mUserViewModel: ReposViewModel

    fun insertDataToDatabase(context: Context) {
        val reposname = "repos"
        val username = "user"
        val description = "age"

            val user = ReposEntity(
                0,
                reposname,
                username,
                description,
                "py",
                "pypy",
                "pypypy",
                "pypypypy",
                "pypypypypy",
                "pypypypypypypy",
                "pypypypypypypypy"
            )
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(context, "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
    }

}