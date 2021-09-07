package com.example.githubtrendingrepositories.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtrendingrepositories.ui.viewmodel.ItemsViewModel
import com.example.githubtrendingrepositories.R

class RecyclerViewAdapter(private val context: Context, private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
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

            if(itemsViewModel.languageColor != "null"){
                holder.languageColorImg.setColorFilter(Color.parseColor(itemsViewModel.languageColor))
            }
            else holder.languageColorImg.setColorFilter(Color.WHITE)

            val isExpandable: Boolean = itemsViewModel.expandable
            holder.expandableLayout.visibility = if(isExpandable) View.VISIBLE else View.GONE

            holder.collapsedLayout.setOnClickListener {
                itemsViewModel.expandable = !itemsViewModel.expandable
                notifyItemChanged(position)
            }
        }catch (e: IllegalArgumentException){
            Log.d("exceptionn", itemsViewModel.languageColor + itemsViewModel.reposname)
        }

        holder.shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, itemsViewModel.url)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }

        holder.openButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(itemsViewModel.url)
            context.startActivity(intent)
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
        val languageColorImg: ImageView = itemView.findViewById(R.id.language_vector)

        val collapsedLayout: RelativeLayout = itemView.findViewById(R.id.collapsed_layout)
        val expandableLayout: LinearLayout = itemView.findViewById(R.id.expanded_layout)

        val shareButton: Button = itemView.findViewById(R.id.share_btn)
        val openButton: Button = itemView.findViewById(R.id.open_btn)
    }
}