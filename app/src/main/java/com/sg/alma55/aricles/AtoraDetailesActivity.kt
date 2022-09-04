package com.sg.alma55.aricles

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sg.alma55.R
import com.sg.alma55.databinding.ActivityArticlesDetailsBinding
import com.sg.alma55.databinding.ActivityAtoraDetailesBinding
import com.sg.alma55.models.Article
import com.sg.alma55.utilities.BaseActivity
import com.sg.alma55.utilities.Constants
import java.lang.reflect.Type

class AtoraDetailesActivity : BaseActivity() {
    lateinit var binding: ActivityAtoraDetailesBinding
    var articles = ArrayList<Article>()
    private var articlesIndex = 0
    private var backGroundColor = ""
    private var textColor = ""
    private var texti = ""
    lateinit var gson: Gson
    lateinit var pref: SharedPreferences
    lateinit var currentArticle: Article
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtoraDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        pref = getSharedPreferences(Constants.SHARPREF_ALMA, MODE_PRIVATE)
        gson = Gson()
        loadArticles()
        articlesIndex = intent.getIntExtra(Constants.ARTICLES_DETALES_INDEX, 0)
        currentArticle = findArticle(articlesIndex)
        draw_current_articles()
    }

    private fun draw_current_articles() {
        backGroundColor = currentArticle.articleBackground
        textColor = currentArticle.articleTextColor
        texti = currentArticle.aricleText

        binding.mainAtoraBackground.setBackgroundColor(Color.parseColor(backGroundColor))
        binding.tvAtoraArticle.setTextColor(Color.parseColor(textColor))
        binding.titleAtoraArticle.setTextColor(Color.parseColor(textColor))
        binding.titleAtoraArticle.text=setTextTitle()
        binding.tvAtoraArticle.text = texti
    }

    private fun setTextTitle(): String =
        "---------------------------------\n" +
                "${currentArticle.aricleTitle}\n" +
                "--------------------------------- "


private fun loadArticles() {
        articles.clear()
        val gson = Gson()
        val pref = getSharedPreferences(Constants.SHARPREF_ALMA, MODE_PRIVATE)
        val json: String? = pref.getString(Constants.SHARPREF_ARTICLRS_ARRAY, null)
        val type: Type = object : TypeToken<ArrayList<Article>>() {}.type
        // val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
        articles = gson.fromJson(json, type)
    }

    private fun findArticle(key: Int): Article {
        for (art in articles) {
            if (art.aricleNum == key) {
                return art
            }
        }
        return Article()
    }
}


/*  private fun activateText() {
       binding.tvAtoraArticle.text = application.assets.open(fileName).bufferedReader().use {
           it.readText()
       }
   }*/
/*  atoraIndex = intent.getIntExtra(Constants.ATORA_DETALES_INDEX, 0)

      when (atoraIndex) {
          1 -> {
              fileName = "atoraFile1"
              backGroundColor="#0A174E"
              textColor="#F5D042"
          }
         2 -> {
              fileName =  "atoraFile2"
              backGroundColor="#2BAE66"
              textColor="#ffffff"
          }
          3 -> {
                fileName = "atoraFile3"
                backGroundColor="#0A174E"
                textColor="#F5D042"
            }*/
/*    4 -> {
     fileName = "myText4"
     backGroundColor="#333D79"
     textColor="#ffffff"
 }
*/

/*    else -> {
        "myText1"
        backGroundColor="#0A174E"
        textColor="#F5D042"
    }
}
binding.mainAtoraBackground           .setBackgroundColor(Color.parseColor(backGroundColor))
binding.tvAtoraArticle.setTextColor(Color.parseColor(textColor))
activateText()
}*/
