package com.sg.alma55.aricles

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sg.alma55.R
import com.sg.alma55.databinding.ActivityArticlesBinding
import com.sg.alma55.databinding.ActivityAtoraBinding
import com.sg.alma55.models.Article
import com.sg.alma55.utilities.BaseActivity
import com.sg.alma55.utilities.Constants
import com.sg.alma55.utilities.FontFamilies
import java.lang.reflect.Type

class AtoraActivity : BaseActivity() {
    lateinit var binding: ActivityAtoraBinding
    lateinit var gson: Gson
    var articles = ArrayList<Article>()
    lateinit var pref: SharedPreferences
    lateinit var button: Button
    val helper = FontFamilies()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAtoraBinding.inflate(layoutInflater)
        setContentView(binding.root)
            pref = getSharedPreferences(Constants.SHARPREF_ALMA, MODE_PRIVATE)
            gson = Gson()
            articles = loadArticles()
            drawLayout()
        }
    private fun drawLayout() {
        for (index in 0 until articles.size){
            val currentA=articles[index]
            if (currentA.aricleNum<20){
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
        val layoutParam: LinearLayout.LayoutParams=  LinearLayout.LayoutParams(
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
        binding.atoraLayout.addView(button,layoutParam)

    }

    private fun setItOnclick(button: Button) {
        button.setOnClickListener {
            when (it.id){
                0 -> {
                }
                else  ->{
                    val intent = Intent(this, AtoraDetailesActivity::class.java)
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

    }

  /*  private fun setupAtoraBottons() {
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
    }*/



/*  private fun loadArticles(){
       articles.clear()
       val gson = Gson()
       val  pref = getSharedPreferences(Constants.SHARPREF_ALMA,MODE_PRIVATE)
       val json: String? = pref.getString(Constants.SHARPREF_ARTICLRS_ARRAY, null)
       val type: Type = object : TypeToken<ArrayList<Article>>() {}.type
       // val type = object : TypeToken<HashMap<Int?, Int?>?>() {}.type
       articles = gson.fromJson(json, type)
   }*/