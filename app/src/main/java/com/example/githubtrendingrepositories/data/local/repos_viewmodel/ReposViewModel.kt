package com.example.githubtrendingrepositories.data.local.repos_viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.githubtrendingrepositories.data.local.database.ReposDatabase
import com.example.githubtrendingrepositories.data.local.database_repository.ReposRepository
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReposViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<ReposEntity>>

    private val repository: ReposRepository

    init {
        val userDao = ReposDatabase.getDatabase(application).reposDao()
        repository = ReposRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addRepos(reposEntity: ReposEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(reposEntity)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ReposEntity>>{
        return repository.searchDatabase(searchQuery)
    }

    fun deleteAllRepos(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUser()
        }
    }

}