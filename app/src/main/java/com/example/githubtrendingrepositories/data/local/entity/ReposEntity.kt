package com.example.githubtrendingrepositories.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//This class defines properties for our SQL database Table

@Entity(tableName = "repos_table")
data class ReposEntity(
    @PrimaryKey
    val rank: Int,
    val reposName: String,
    val userName: String,
    val description: String,
    val language: String,
    val url: String,
    val languageColor: String,
    val totalStars: String,
    val forks: String,
    var expandable: Boolean = false
)