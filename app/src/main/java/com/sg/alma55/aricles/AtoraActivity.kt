package com.sg.alma55.aricles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sg.alma55.R
import com.sg.alma55.databinding.ActivityAtoraBinding
import com.sg.alma55.models.Article
import com.sg.alma55.utilities.BaseActivity
import com.sg.alma55.utilities.Constants
import java.lang.reflect.Type

class AtoraActivity : BaseActivity() {
    lateinit var binding: ActivityAtoraBinding
   // var articles=ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAtoraBinding.inflate(layoutInflater)
        setContentView(binding.root)
       //loadArticles()
        setupAtoraBottons()
    }
  /*  private fun loadArticles(){
        articles.clear()
        val gson = Gson()
        val  pref = getSharedPreferences(Constants.SHARPREF_ALMA,MODE_PRIVATE)
        val json: String? = pref.getString(Constants.SHARPREF_ARTICLRS_ARRAY, null)
        val type: Type = object : TypeToken<ArrayList<Article>>() {}.type
        // val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
        articles = gson.fromJson(json, type)
    }*/
    private fun setupAtoraBottons() {
        binding.btnBereshit.setOnClickListener {
            logi("AtoraActicity 37    send 11")
            val intent = Intent(this,AtoraDetailesActivity::class.java)
            intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 11)
            startActivity(intent)
        }
        binding.btnBeforBereshit.setOnClickListener {
            val intent = Intent(this,AtoraDetailesActivity::class.java)
            intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 12)
            startActivity(intent)
        }
        binding.btnKomot.setOnClickListener {
            val intent = Intent(this,AtoraDetailesActivity::class.java)
            intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 13)
            startActivity(intent)
        }
    }
}