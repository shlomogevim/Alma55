package com.sg.alma55.aricles

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sg.alma55.R
import com.sg.alma55.databinding.ActivityArticlesBinding
import com.sg.alma55.modeles.Post
import com.sg.alma55.models.Article
import com.sg.alma55.utilities.BaseActivity
import com.sg.alma55.utilities.Constants
import com.sg.alma55.utilities.Constants.SHARPREF_ALMA
import com.sg.alma55.utilities.Constants.SHARPREF_ARTICLRS_ARRAY
import com.sg.alma55.utilities.HelpExplanationActivity
import java.lang.reflect.Type

class ArticlesActivity : BaseActivity() {
    lateinit var binding: ActivityArticlesBinding
    lateinit var gson: Gson
    var articles = ArrayList<Article>()
    lateinit var pref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences(SHARPREF_ALMA, MODE_PRIVATE)
        gson = Gson()
        articles = loadArticles()
        setupBottons()
    }

    private fun showArticles() {
        for (index in 0 until articles.size) {
            logi("ArticleActivity 39     index=$index  articles[index].aricleNum=${articles[index].aricleNum}")
        }
    }

    private fun loadArticles(): ArrayList<Article> {
        articles.clear()
        val json: String? = pref.getString(Constants.SHARPREF_ARTICLRS_ARRAY, null)
        val type: Type = object : TypeToken<ArrayList<Article>>() {}.type
        // val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
        val arr: ArrayList<Article> = gson.fromJson(json, type)
        return arr
    }

    private fun findArticle(key: Int): Article {
        for (art in articles) {
            if (art.aricleNum == key) {
                return art
            }
        }
        return Article()
    }

    private fun setupBottons() {
        binding.btnAtora.text = "התורה"
        binding.btnAtora.setOnClickListener {
            val intent = Intent(this, AtoraActivity::class.java)
            startActivity(intent)
        }
        val article21 = findArticle(21)
        binding.btnYakam.text = article21.aricleTitle
        binding.btnYakam.setOnClickListener {
            val intent = Intent(this, ArticlesDetails::class.java)
            intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 21)
            startActivity(intent)
        }
        val art22 = findArticle(22)
        binding.btnNachman1.text = art22.aricleTitle
        binding.btnNachman1.setOnClickListener {
            val intent = Intent(this, ArticlesDetails::class.java)
            intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 22)
            startActivity(intent)
        }
        val art23 = findArticle(23)
        binding.btnOlamon.text = art23.aricleTitle
        binding.btnOlamon.setOnClickListener {
            val intent = Intent(this, ArticlesDetails::class.java)
            intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 23)
            startActivity(intent)
        }

    }

}