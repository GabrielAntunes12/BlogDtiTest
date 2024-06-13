package com.example.blogdtitest.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blogdtitest.databinding.ActivityDecideBinding
import com.example.blogdtitest.util.API_DECIDED_REST
import org.koin.android.ext.android.inject

class DecideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDecideBinding
    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDecideBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupButtons()
    }

    private fun setupButtons() {
        binding.mockButton.setOnClickListener {
            sharedPreferences.edit().putBoolean(API_DECIDED_REST, false).apply()
            intentMain()
        }

        binding.apiRestfulButton.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            sharedPreferences.edit().putBoolean(API_DECIDED_REST, true).apply()
            intentMain()
        }
    }

    private fun intentMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}