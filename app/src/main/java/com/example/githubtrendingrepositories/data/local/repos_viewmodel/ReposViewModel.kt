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

class ReposViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<ReposEntity>>
    private val repository: ReposRepository

    // initialize DAO and Repository
    init {
        val userDao = ReposDatabase.getDatabase(application).reposDao()
        repository = ReposRepository(userDao)
        readAllData = repository.readAllData
    }

    // Calling repository addRepos function inside Coroutine viewModelScope
    fun addRepos(reposEntity: ReposEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRepos(reposEntity)
        }
    }

    // Simple call to repository searchDatabase function
    fun searchDatabase(searchQuery: String): LiveData<List<ReposEntity>> {
        return repository.searchDatabase(searchQuery)
    }

    // Calling repository deleteAllRepos function inside Coroutine viewModelScope
    fun deleteAllRepos() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllRepos()
        }
    }

}