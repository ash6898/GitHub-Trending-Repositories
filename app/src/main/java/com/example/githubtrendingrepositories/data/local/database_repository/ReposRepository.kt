package com.example.githubtrendingrepositories.data.local.database_repository

import androidx.lifecycle.LiveData
import com.example.githubtrendingrepositories.data.local.dao.ReposDao
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity

class ReposRepository(private val reposDao: ReposDao) {

    //Initializing List of ReposEntity as LiveData
    val readAllData: LiveData<List<ReposEntity>> = reposDao.readAllRepos()

    //function to call addrepos dao method
    suspend fun addRepos(reposEntity: ReposEntity) {
        reposDao.addRepos(reposEntity)
    }

    //function to call deleteAllRepos dao method
    suspend fun deleteAllRepos() {
        reposDao.deleteAllRepos()
    }

    //function to call searchDatabase dao method
    fun searchDatabase(searchQuery: String): LiveData<List<ReposEntity>> {
        return reposDao.searchDatabase(searchQuery)
    }

}