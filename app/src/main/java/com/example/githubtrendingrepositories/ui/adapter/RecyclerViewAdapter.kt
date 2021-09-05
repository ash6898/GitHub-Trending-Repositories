package com.example.githubtrendingrepositories.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtrendingrepositories.ui.viewmodel.ItemsViewModel
import com.example.githubtrendingrepositories.R

class RecyclerViewAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_repos_for_recyclerview, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.reposname.text = ItemsViewModel.reposname

        // sets the text to the textview from our itemHolder class
        holder.username.text = ItemsViewModel.username

        holder.description.text = ItemsViewModel.description

        holder.language.text = ItemsViewModel.language

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val reposname: TextView = itemView.findViewById(R.id.reposname_txt)
        val username: TextView = itemView.findViewById(R.id.username_txt)
        val description: TextView = itemView.findViewById(R.id.description_txt)
        val language: TextView = itemView.findViewById(R.id.language_txt)
    }
}