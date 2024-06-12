package com.example.blogdtitest.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.blogdtitest.databinding.ItemPostBinding
import com.example.blogdtitest.interfaces.ClickDetailsInterface
import com.example.blogdtitest.model.ItemPostData

class BlogPostsViewHolder(
    private val binding: ItemPostBinding,
    private val clickDetailsInterface: ClickDetailsInterface
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemPostData) {
        binding.titleText.text = item.title
        binding.dateText.text = item.createdAt
        binding.containerItemPost.setOnClickListener { clickDetailsInterface.onClickDetails(item) }
    }
}