package com.example.githubtrendingrepositories.data.local.database_repository

import androidx.lifecycle.LiveData
import com.example.githubtrendingrepositories.data.local.dao.ReposDao
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity

class ReposRepository(private val reposDao: ReposDao) {

    val readAllData: LiveData<List<ReposEntity>> = reposDao.readAllRepos()

    suspend fun addUser(reposEntity: ReposEntity){
        reposDao.addRepos(reposEntity)
    }

    suspend fun deleteAllUser(){
        reposDao.deleteAllRepos()
    }

}