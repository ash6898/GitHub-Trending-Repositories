package com.example.githubtrendingrepositories.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRepos(reposEntity: ReposEntity)

    @Query("SELECT * FROM repos_table ORDER BY totalStars ASC")
    fun readAllData(): LiveData<List<ReposEntity>>
}