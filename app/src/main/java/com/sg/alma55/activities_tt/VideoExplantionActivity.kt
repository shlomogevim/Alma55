package com.sg.alma55.activities_tt

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sg.alma55.databinding.ActivityVideoExplantionBinding
import com.sg.alma55.modeles.Post
import com.sg.alma55.utilities.BaseActivity
import com.sg.alma55.utilities.Constants
import java.lang.reflect.Type

class VideoExplantionActivity : BaseActivity() {
    lateinit var pref: SharedPreferences
    lateinit var currentPost: Post
    private lateinit var binding: ActivityVideoExplantionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVideoExplantionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = getSharedPreferences(Constants.SHARPREF_ALMA, Context.MODE_PRIVATE)
        currentPost=loadCurrentPost()
        drawCurrentVideoComment()
    }
    private fun drawCurrentVideoComment() {
        val backGroundColor = "#0A174E"
       val textColor = "#F5D042"


//        val  texti = "אין עדיין הערות על השיר הזה"
        val  texti = currentPost.videoText

        binding.mainBackground.setBackgroundColor(Color.parseColor(backGroundColor))
        binding.tvArticleVideo.setTextColor(Color.parseColor(textColor))
      /*  binding.tvTitle.setTextColor(Color.parseColor(textColor))
        binding.tvTitle.text = setTextTitle()*/
        binding.tvArticleVideo.text =texti
    }
 /*   private fun setTextTitle(): String =
        "---------------------------------\n" +
                "הערות על קטע השיר הזה\n" +
                "--------------------------------- "*/

    fun loadCurrentPost(): Post {
        val gson = Gson()
        val json: String? = pref.getString(Constants.SHARPREF_CURRENT_POST, null)
        val type: Type = object : TypeToken<Post>() {}.type
        val post: Post = gson.fromJson(json, type)
        return post
    }
}