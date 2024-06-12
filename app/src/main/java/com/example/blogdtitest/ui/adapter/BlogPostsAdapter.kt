package com.example.blogdtitest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blogdtitest.databinding.ItemPostBinding
import com.example.blogdtitest.interfaces.ClickDetailsInterface
import com.example.blogdtitest.model.ItemPostData
import com.example.blogdtitest.ui.adapter.viewholder.BlogPostsViewHolder

class BlogPostsAdapter(private var clickDetailsInterface: ClickDetailsInterface) : RecyclerView.Adapter<BlogPostsViewHolder>() {
    private var listPost: MutableList<ItemPostData> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogPostsViewHolder {
        val itemView = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogPostsViewHolder(itemView, clickDetailsInterface)
    }

    override fun getItemCount() = listPost.size

    override fun onBindViewHolder(holder: BlogPostsViewHolder, position: Int) {
        holder.bind(listPost[position])
    }

    fun addList(listPostAdd : List<ItemPostData>){
        listPost.clear()
        listPost.addAll(listPostAdd)
        notifyDataSetChanged()
    }

}