package com.example.githubtrendingrepositories.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.githubtrendingrepositories.R
import com.example.githubtrendingrepositories.data.local.repos_viewmodel.InsertDataToDatabase
import com.example.githubtrendingrepositories.data.remote.api.GitHubTrendingAPI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiObj = GitHubTrendingAPI()
        val viewTransformation = ViewTransformation()
        val insertData = InsertDataToDatabase()

        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val noInternet = findViewById<LinearLayout>(R.id.no_internet)
        val recyclerView = findViewById<RecyclerView>(R.id.listView)
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh_layout)
        val retryButton = findViewById<Button>(R.id.retry_btn)

        apiObj.getData(
            this,
            this,
            this,
            recyclerView,
            progressBar,
            swipeRefreshLayout,
            noInternet,
            false
        )

        retryButton.setOnClickListener {
            viewTransformation.showProgressBar(progressBar, swipeRefreshLayout, noInternet, recyclerView)

            apiObj.getData(this,
                this,
                this,
                recyclerView,
                progressBar,
                swipeRefreshLayout,
                noInternet,
            false)
        }

        swipeRefreshLayout.setOnRefreshListener {

            val fromSwipe: Boolean = true

            apiObj.getData(
                this,
                this,
                this,
                recyclerView,
                progressBar,
                swipeRefreshLayout,
                noInternet,
                fromSwipe
            )

            swipeRefreshLayout.isRefreshing = false

            if(checkForInternet(this)){
                Toast.makeText(this,"Refresh Success",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"No Internet",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}


