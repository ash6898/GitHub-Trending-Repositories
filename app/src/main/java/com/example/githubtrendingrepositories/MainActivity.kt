package com.example.githubtrendingrepositories

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val getTrendBtn = findViewById<Button>(R.id.trend_btn)

        getData()

        //getTrendBtn.setOnClickListener {
            //Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            //getData()
        //}
    }

    fun getData() {
        //val textView = findViewById<TextView>(R.id.update_txt)
// ...

// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://gh-trending-api.herokuapp.com/repositories"

// Request a string response from the provided URL.
        val stringRequest = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener
            { response ->

                //textView.text = res

                //val listView = findViewById<ListView>(R.id.listView)
                //val listItems = arrayOfNulls<String>(response.length())

                for (i in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(i)
                    val rank = jsonObject.get("rank").toString()
                    val username = jsonObject.get("username").toString()
                    val repositoryName = jsonObject.get("repositoryName").toString()
                    val description = jsonObject.get("description").toString()
                    val language = jsonObject.get("language").toString()
                    val res = rank + "\n" + username + "\n" + repositoryName + "\n" + description + "\n" + language
                    //listItems[i] = res
                }


                //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
                //listView.adapter = adapter




                // getting the recyclerview by its id
                val recyclerview = findViewById<RecyclerView>(R.id.listView)

                // this creates a vertical layout Manager
                recyclerview.layoutManager = LinearLayoutManager(this)

                // ArrayList of class ItemsViewModel
                val data = ArrayList<ItemsViewModel>()

                // This loop will create 20 Views containing
                // the image with the count of view
                for (i in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(i)
                    val username = jsonObject.get("username").toString()
                    val repositoryName = jsonObject.get("repositoryName").toString()
                    val description = jsonObject.get("description").toString()
                    val language = jsonObject.get("language").toString()
                    data.add(ItemsViewModel(repositoryName, username, description, language))
                }

                // This will pass the ArrayList to our Adapter
                val adapter = RecyclerViewaAdapter(data)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

                val progressbar = findViewById<ProgressBar>(R.id.progressbar)

                progressbar.visibility = View.GONE
                recyclerview.visibility = View.VISIBLE

            },
            {
                Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()})
                //textView.text = "That didn't work!" })

// Add the request to the RequestQueue.
                queue.add(stringRequest)
            }
    }

