package com.sg.alma55.aricles

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sg.alma55.R
import com.sg.alma55.databinding.ActivityArticlesBinding
import com.sg.alma55.models.Article
import com.sg.alma55.utilities.BaseActivity
import com.sg.alma55.utilities.Constants
import com.sg.alma55.utilities.Constants.SHARPREF_ALMA
import com.sg.alma55.utilities.FontFamilies
import java.lang.reflect.Type

class ArticlesActivity : BaseActivity() {
    lateinit var binding: ActivityArticlesBinding
    lateinit var gson: Gson
    var articles = ArrayList<Article>()
    lateinit var pref: SharedPreferences
    lateinit var button: Button
    val helper = FontFamilies()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences(SHARPREF_ALMA, MODE_PRIVATE)
        gson = Gson()
        articles = loadArticles()
        drawLayout()
    }

    private fun drawLayout() {
         for (index in 0 until articles.size){
             if (index==0){
                 creatButton("התורה",0)
             }
             val currentA=articles[index]
             if (currentA.aricleNum>20){
                 creatButton(currentA.aricleTitle,currentA.aricleNum)
             }
         }
    }

    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun Int.toSp():Float=(this.toFloat()/resources.displayMetrics.scaledDensity)

    private fun creatButton(title:String,id:Int) {
        button = Button(this)
        button.id = id
        setItOnclick(button)
        button.text=title
        button.setBackgroundResource(R.drawable.button_background1)
        val fontAddress = helper.getFamilyFont(103)
        button.typeface = ResourcesCompat.getFont(this, fontAddress)
        button.textSize=16f
        button.setTextColor(Color.parseColor("#ffffff"))
       // button.setBackgroundColor(Color.parseColor("#FF018786"))
        val  radius=16
        val shape = GradientDrawable()
        shape.cornerRadius = radius.toPx().toFloat()
        shape.setColor(Color.parseColor("#FF018786"))
        button.background = shape
        val layoutParam:LinearLayout.LayoutParams=  LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParam.marginStart=16.toPx()
        layoutParam.marginEnd=16.toPx()
        layoutParam.topMargin=16.toPx()
        button.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
           binding.articlesLayout.addView(button,layoutParam)

    }

    private fun setItOnclick(button: Button) {
        button.setOnClickListener {
            when (it.id){
                0 -> {
                    val intent = Intent(this, AtoraActivity::class.java)
                    startActivity(intent)
                }
              else  ->{
                                   val intent = Intent(this, ArticlesDetails::class.java)
                  intent.putExtra(Constants.ARTICLES_DETALES_INDEX, it.id)
                  startActivity(intent)
               }
            }

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

   /* private fun setupBottons() {
        *//* binding.btnAtora.text = "התורה"
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
                    binding.btnNachman1.text = "שיחות עם נחמן"
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
                     val art24 = findArticle(24)
                   binding.btnZeShi.text = art24.aricleTitle
                   binding.btnZeShi.setOnClickListener {
                       val intent = Intent(this, ArticlesDetails::class.java)
                       intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 24)
                       startActivity(intent)
                   }
                 val art25 = findArticle(25)
               binding.btnLifePoint.text = art25.aricleTitle
               binding.btnLifePoint.setOnClickListener {
                   val intent = Intent(this, ArticlesDetails::class.java)
                   intent.putExtra(Constants.ARTICLES_DETALES_INDEX, 25)
                   startActivity(intent)
               }*//*
    }*/

   /* private fun findArticle(key: Int): Article {
        for (art in articles) {
            if (art.aricleNum == key) {
                return art
            }
        }
        return Article()
    }*/

   


    /*  private fun showArticles() {
        for (index in 0 until articles.size) {
            logi("ArticleActivity 39     index=$index  articles[index].aricleNum=${articles[index].aricleNum}")
        }
    }*/

}