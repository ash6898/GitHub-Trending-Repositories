package com.example.githubtrendingrepositories.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRepos(reposEntity: ReposEntity)

    @Query("SELECT * FROM repos_table ORDER BY rank ASC")
    fun readAllRepos(): LiveData<List<ReposEntity>>

    @Query("SELECT COUNT('rank') FROM repos_table")
    fun getTableSize(): Int

    @Query("DELETE FROM repos_table")
    suspend fun deleteAllRepos()

}