package com.example.blogdtitest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blogdtitest.databinding.ActivityMainBinding
import com.example.blogdtitest.interfaces.ClickDetailsInterface
import com.example.blogdtitest.model.ItemPostData
import com.example.blogdtitest.ui.adapter.BlogPostsAdapter
import com.example.blogdtitest.ui.states.StatesRetrieveList
import com.example.blogdtitest.util.DETAILS_ITEM_POST_DATA
import com.example.blogdtitest.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

class MainActivity : AppCompatActivity(), ClickDetailsInterface {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainActivityViewModel>()
    private val adapter = BlogPostsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupRecycler()
        setupButtons()
        setupObservers()
        retrieveList()
    }

    private fun setupButtons() {
        binding.appCompatButton.setOnClickListener {
            val intent = Intent(this, NewPostActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecycler() {
            binding.recyclerPosts.layoutManager = LinearLayoutManager(this)
            binding.recyclerPosts.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.successRetrieveList.observe(this){
            when(it){
                is StatesRetrieveList.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is StatesRetrieveList.SuccessRetrieveList -> {
                    adapter.addList(it.list)
                }
            }
        }
    }

    private fun retrieveList() {
        viewModel.retrieveList()
    }

    override fun onClickDetails(itemPostData: ItemPostData) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DETAILS_ITEM_POST_DATA, itemPostData as Serializable)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        viewModel.retrieveList()
    }
}