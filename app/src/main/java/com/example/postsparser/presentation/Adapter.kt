package com.example.postsparser.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.postsparser.R
import com.example.postsparser.domain.Post
import javax.inject.Inject

class PostAdapter @Inject constructor(
    private val onPostClickListener: OnPostClickListener
) : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tvUserName)
        private val emailTextView: TextView = itemView.findViewById(R.id.tvPost)

        fun bind(post: Post) {
            nameTextView.text = post.title
            emailTextView.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)

        holder.itemView.setOnClickListener {
            onPostClickListener.onPostClick(post.id)
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
}
