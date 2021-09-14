package com.example.githubtrendingrepositories.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.githubtrendingrepositories.R
import com.example.githubtrendingrepositories.data.local.repos_viewmodel.ReposViewModel
import com.example.githubtrendingrepositories.data.remote.api.GithubApi
import com.example.githubtrendingrepositories.ui.adapter.RecyclerViewAdapter

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var reposViewModel: ReposViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiObj = GithubApi()
        val viewTransformation = ViewTransformation()

        // Initialize View variables
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val noInternet = findViewById<LinearLayout>(R.id.no_internet)
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh_layout)
        val retryButton = findViewById<Button>(R.id.retry_btn)
        recyclerView = findViewById(R.id.listView)

        reposViewModel = ViewModelProvider(this).get(ReposViewModel::class.java)

        // Calling GithubApi Class getData to get data from API
        apiObj.getData(
            this,
            this,
            this,
            recyclerView,
            progressBar,
            swipeRefreshLayout,
            noInternet
        )

        // Setting OnClickListener for the "NO INTERNET Retry Button"
        retryButton.setOnClickListener {
            if (checkForInternet(this)) {
                viewTransformation.showProgressBar(
                    progressBar,
                    swipeRefreshLayout,
                    noInternet,
                    recyclerView
                )

                // If Retry Button clicked getData function is called
                apiObj.getData(
                    this,
                    this,
                    this,
                    recyclerView,
                    progressBar,
                    swipeRefreshLayout,
                    noInternet
                )
            } else {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
            }
        }

        // Setting OnRefreshListener for Swipe Refresh Layout
        swipeRefreshLayout.setOnRefreshListener {

            // Checking for internet connection
            if (checkForInternet(this)) {

                // Calling getData function of GithubApi
                apiObj.getData(
                    this,
                    this,
                    this,
                    recyclerView,
                    progressBar,
                    swipeRefreshLayout,
                    noInternet
                )

                // Toast to display "Refresh is Success"
                Toast.makeText(this, "Refresh Success", Toast.LENGTH_SHORT).show()
            } else {
                // Toast to display "No Internet"
                Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show()
            }

            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M or greater we need to use the
        // NetworkCapabilities to check what type of network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport, or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


    // This Function initialize menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    // This function is called after submitting search text
    override fun onQueryTextSubmit(query: String?): Boolean {
        // Checking query is not null
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    // This function is called when we start typing
    override fun onQueryTextChange(query: String?): Boolean {
        // Checking query is not null
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }


    // Function to call repository searchDatabase by passing user query
    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        //Observing LiveData
        reposViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                // Setting data got from search query in database and displaying it in RecyclerView
                val adapter = RecyclerViewAdapter(this, it)
                recyclerView.adapter = adapter
            }
        })
    }
}


