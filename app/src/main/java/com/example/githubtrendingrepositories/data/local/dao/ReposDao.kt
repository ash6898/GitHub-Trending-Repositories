package com.example.githubtrendingrepositories.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRepos(reposEntity: ReposEntity)

    @Update
    suspend fun updateRepos(reposEntity: ReposEntity)

    @Delete
    suspend fun deleteRepos(reposEntity: ReposEntity)

    @Query("DELETE FROM repos_table")
    suspend fun deleteAllRepos()

    @Query("SELECT * FROM repos_table ORDER BY id ASC")
    fun readAllRepos(): LiveData<List<ReposEntity>>

}