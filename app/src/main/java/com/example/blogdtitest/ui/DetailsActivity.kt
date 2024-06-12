package com.example.blogdtitest.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blogdtitest.R
import com.example.blogdtitest.databinding.ActivityDetailsBinding
import com.example.blogdtitest.databinding.ActivityNewPostBinding
import com.example.blogdtitest.model.ItemPostData
import com.example.blogdtitest.util.DETAILS_ITEM_POST_DATA

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupButtons()
        getData()
    }

    private fun setupButtons() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun getData() {
        val itemPostData : ItemPostData?
        val extras = intent.extras
        if (extras != null) {
            itemPostData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                extras.getSerializable(DETAILS_ITEM_POST_DATA, ItemPostData::class.java)
            }else {
                extras.getSerializable(DETAILS_ITEM_POST_DATA) as ItemPostData
            }
            itemPostData?.let { buildScreen(it) }
        }else{
            Toast.makeText(this, "Erro em recuperar dados", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun buildScreen(itemPostData: ItemPostData) {
        binding.titleValue.text = itemPostData.title
        binding.dateValue.text = itemPostData.createdAt
        binding.descValue.text = itemPostData.description
    }
}