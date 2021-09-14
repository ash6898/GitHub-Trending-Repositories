
# GitHub Trending Repositories
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Download](https://img.shields.io/badge/Kotlin-1.3.20-brightgreen.svg?style=flat&logo=kotlin)](https://kotlinlang.org/docs/reference/whatsnew13.html)
[![Download](https://img.shields.io/badge/Gradle-7.0.2-brightgreen.svg?style=flat&logo=android)](https://docs.gradle.org/7.0.2/release-notes.html)

### FEATURES OF GitHub Trending Repositories

 - [x] Get Trending Repositories From GitHub Using API
	 - https://gh-trending-api.herokuapp.com/docs
 - [x] Provides Offline Support By Storing Repositories In Local Database
 - [x] Search Option Is Available To Filter Repositories With 
	 -  Language
	 -  User Name
	 - Repository Name
	 - Keywords

## Demo

### Loading state & List of trending repositories
<img src="https://github.com/ash6898/GitHub-Trending-Repositories/media/ProgressBar_and_Expand_Collapse.gif" width="200" style="max-width:100%;">

### Pull to refresh to fetch latest data
<img src="https://github.com/ash6898/GitHub-Trending-Repositories/media/SwipeRefresh_with_Internet.gif" width="200" style="max-width:100%;">
<img src="https://github.com/ash6898/GitHub-Trending-Repositories/media/LocalDB_without_Internet_and_SwipeRefresh.gif" width="200" style="max-width:100%;">

### Search repositories
<img src="https://github.com/ash6898/GitHub-Trending-Repositories/media/Search_Query.gif" width="200" style="max-width:100%;">

### Empty state with retry option
<img src="https://github.com/ash6898/GitHub-Trending-Repositories/media/Display_noInternet.gif" width="200" style="max-width:100%;">

### The app includes the following main components:

- User Friendly UI
- Storing data in local database.
- A web API service.
- A ViewModel that provides data specific for the UI.

#### App Packages

-   **data**  - contains
    -   **local**  -  Contains Database Classes. Used Kotlin Room Library for Database
    -   **remote.api**  - Contains API Class To Fetch data from remote 
-   **ui**  - contains classes needed to display Activity.

#### App Specifications

-   **[Minimum SDK 21](https://android-arsenal.com/api?level=21#l21)**
-   **[Kotlin - 1.5.20](https://kotlinlang.org/docs/releases.html#release-details)**
-  **[MVVM Architecture](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)**
- **[Room Library](https://developer.android.com/jetpack/androidx/releases/room#2.3.0) - To implement database**
- **[androidx.lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - To perform actions in response to a change in the lifecycle status of another component, such as activities and fragments**
- **[Volley](https://developer.android.com/training/volley) - For API integration**