package com.example.blogdtitest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.blogdtitest.R
import com.example.blogdtitest.databinding.ActivityMainBinding
import com.example.blogdtitest.databinding.ActivityNewPostBinding
import com.example.blogdtitest.ui.adapter.BlogPostsAdapter
import com.example.blogdtitest.ui.states.StatesAddPost
import com.example.blogdtitest.viewmodel.MainActivityViewModel
import com.example.blogdtitest.viewmodel.NewPostViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostBinding
    private val viewModel by viewModel<NewPostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupObservers()
        setupButton()
    }

    private fun setupObservers() {
        viewModel.stateAddPost.observe(this){
            when(it){
                is StatesAddPost.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is StatesAddPost.SuccessAddPost -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun setupButton() {
        binding.backButton.setOnClickListener {
            finish()
        }
        binding.addPostButton.setOnClickListener {
            viewModel.addPost(binding.titleInsert.text.toString(), binding.descInsert.text.toString())
        }
    }
}