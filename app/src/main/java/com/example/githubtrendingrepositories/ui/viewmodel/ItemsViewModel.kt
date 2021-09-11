package com.example.githubtrendingrepositories.ui.viewmodel

data class ItemsViewModel(val reposname: String,
                          val username: String,
                          val description: String,
                          val language: String,
                          val url: String,
                          val languageColor: String,
                          val totalStars: String,
                          val starsToday: String,
                          val forks: String,
                          val rgbColor: String,
                          var expandable: Boolean = false) {
}