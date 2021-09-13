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
import com.example.githubtrendingrepositories.data.local.repos_viewmodel.InsertDataToDatabase
import com.example.githubtrendingrepositories.data.local.repos_viewmodel.ReposViewModel
import com.example.githubtrendingrepositories.data.remote.api.GitHubTrendingAPI
import com.example.githubtrendingrepositories.ui.adapter.RecyclerViewAdapter

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var reposViewModel: ReposViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiObj = GitHubTrendingAPI()
        val viewTransformation = ViewTransformation()
        val insertData = InsertDataToDatabase()

        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val noInternet = findViewById<LinearLayout>(R.id.no_internet)

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh_layout)
        val retryButton = findViewById<Button>(R.id.retry_btn)

        reposViewModel = ViewModelProvider(this).get(ReposViewModel::class.java)
        recyclerView = findViewById<RecyclerView>(R.id.listView)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchDatabase(newText)
        }
        return true
    }

    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"

        reposViewModel.searchDatabase(searchQuery).observe(this,{list ->
            list.let{
                val adapter = RecyclerViewAdapter(this, it)
                recyclerView.adapter = adapter
            }
        })
    }
}


