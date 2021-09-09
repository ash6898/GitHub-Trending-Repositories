package com.example.githubtrendingrepositories.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos_table")
data class ReposEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val reposname: String,
    val username: String,
    val description: String,
    val language: String,
    val url: String,
    val languageColor: String,
    val totalStars: String,
    val starsToday: String,
    val forks: String,
    val rgbColor: String
)