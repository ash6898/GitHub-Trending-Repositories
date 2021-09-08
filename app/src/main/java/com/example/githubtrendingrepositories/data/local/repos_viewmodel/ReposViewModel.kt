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

class ReposViewMode(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<ReposEntity>>
    private val repository: ReposRepository
    init {
        val reposDao = ReposDatabase.getDatabase(application).reposDao()
        repository = ReposRepository(reposDao)
        readAllData = repository.readAllData
    }

    fun addRepos(reposEntity: ReposEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addRepos(reposEntity)
        }
    }
}