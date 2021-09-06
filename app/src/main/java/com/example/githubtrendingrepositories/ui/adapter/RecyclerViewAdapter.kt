package com.example.githubtrendingrepositories.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
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
        val itemsViewModel = mList[position]
        try {
            // sets the image to the imageview from our itemHolder class
            holder.reposname.text = itemsViewModel.reposname
            holder.username.text = itemsViewModel.username
            holder.description.text = itemsViewModel.description
            holder.language.text = itemsViewModel.language
            holder.totalStars.text = itemsViewModel.totalStars
            holder.forks.text = itemsViewModel.forks
            holder.languageColor.text = itemsViewModel.languageColor
            if(itemsViewModel.languageColor != "null"){
                holder.languageColorImg.setColorFilter(Color.parseColor(itemsViewModel.languageColor))
            }
            else{
                holder.languageColorImg.visibility = View.GONE
            }
            val isExpandable: Boolean = itemsViewModel.expandable
            holder.expandableLayout.visibility = if(isExpandable) View.VISIBLE else View.GONE

            holder.collapsedLayout.setOnClickListener {
                itemsViewModel.expandable = !itemsViewModel.expandable
                notifyItemChanged(position)
            }
        }catch (e: IllegalArgumentException){
            Log.d("exceptionn", itemsViewModel.languageColor + itemsViewModel.reposname)
            //holder.languageColorImg.visibility = View.INVISIBLE
        }
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
        val totalStars: TextView = itemView.findViewById(R.id.stars_txt)
        val forks: TextView = itemView.findViewById(R.id.forks_txt)
        val languageColor: TextView = itemView.findViewById(R.id.author_txt)
        val languageColorImg: ImageView = itemView.findViewById(R.id.languge_color_img)

        val collapsedLayout: RelativeLayout = itemView.findViewById(R.id.collapsed_layout)
        val expandableLayout: RelativeLayout = itemView.findViewById(R.id.expanded_layout)
    }
}