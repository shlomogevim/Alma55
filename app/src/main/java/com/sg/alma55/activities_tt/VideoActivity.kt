package com.sg.alma55.activities_tt

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.sg.alma55.R
import com.sg.alma55.activities.MainActivity
import com.sg.alma55.adapters.PostAdapter
import com.sg.alma55.databinding.ActivityVedioBinding
import com.sg.alma55.modeles.Post
import com.sg.alma55.utilities.BaseActivity
import com.sg.alma55.utilities.Constants
import com.sg.alma55.utilities.Constants.CURRENT_URL
import java.lang.reflect.Type

class VideoActivity : BaseActivity() {
    private lateinit var binding: ActivityVedioBinding
 //   private var url=""
    lateinit var pref: SharedPreferences
    lateinit var currentPost: Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVedioBinding.inflate(layoutInflater)
       window.setFlags(
           WindowManager.LayoutParams.FLAG_FULLSCREEN,
           WindowManager.LayoutParams.FLAG_FULLSCREEN
       )
        setContentView(binding.root)

       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        pref = getSharedPreferences(Constants.SHARPREF_ALMA, Context.MODE_PRIVATE)
        currentPost = loadCurrentPost()
        pref.edit().putInt(Constants.SHARPREF_CURRENT_POST_NUM, currentPost.postNum).apply()
//        logi("gg","VideoActivity => currentPostNum = ${currentPost.postNum}")

        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                   val url=currentPost.videoUrl
                   youTubePlayer.loadVideo(url, 0f)
            }
        })
        binding.videoExplenationBtn.setOnClickListener {
            startActivity(Intent(this,VideoExplantionActivity::class.java))
            finish()
        }
    }

    fun loadCurrentPost(): Post {
        val gson = Gson()
        val json: String? = pref.getString(Constants.SHARPREF_CURRENT_POST, null)
        val type: Type = object : TypeToken<Post>() {}.type
        val post: Post = gson.fromJson(json, type)
        return post
    }

}
