package com.sg.alma55.aricles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sg.alma55.R
import com.sg.alma55.databinding.ActivityArticlesBinding
import com.sg.alma55.utilities.Constants
import com.sg.alma55.utilities.HelpExplanationActivity

class ArticlesActivity : AppCompatActivity() {
    lateinit var binding: ActivityArticlesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottons()
    }

    private fun setupBottons() {
        binding.btnAtora.setOnClickListener {
            val intent = Intent(this, ArticlesDetails::class.java)
            intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 1)
            startActivity(intent)
        }
        binding.btnYakam.setOnClickListener {
            val intent = Intent(this, ArticlesDetails::class.java)
            intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 2)
            startActivity(intent)
        }
        binding.btnNachman1.setOnClickListener {
            val intent = Intent(this, ArticlesDetails::class.java)
            intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 3)
            startActivity(intent)
        }

    }

}