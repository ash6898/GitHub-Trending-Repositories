package com.example.githubtrendingrepositories.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity

@Dao
interface ReposDao {

    //Query to insert new repository
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRepos(reposEntity: ReposEntity)

    //Query to read all repository stored in database
    @Query("SELECT * FROM repos_table ORDER BY rank ASC")
    fun readAllRepos(): LiveData<List<ReposEntity>>

    //Query to search in database
    @Query("SELECT * FROM repos_table WHERE reposname LIKE :searchQuery OR username LIKE :searchQuery OR description LIKE :searchQuery OR language LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<ReposEntity>>

    //Query to delete all repository stored
    @Query("DELETE FROM repos_table")
    suspend fun deleteAllRepos()

}