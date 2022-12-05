package com.sg.alma55.activities_tt

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sg.alma55.R
import com.sg.alma55.databinding.ActivityVideoExplantionBinding
import com.sg.alma55.utilities.Constants.VIDEO_TEXT

class VideoExplanationActivity : AppCompatActivity() {
    lateinit var binding: ActivityVideoExplantionBinding
    var textVideo=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVideoExplantionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textVideo= intent.getStringExtra(VIDEO_TEXT).toString()

        drawCurrentVideoComment()
    }
    private fun drawCurrentVideoComment() {
        val backGroundColor = "#0A174E"
        val textColor = "#F5D042"

        binding.mainBackground.setBackgroundColor(Color.parseColor(backGroundColor))
        binding.tvArticleVideo.setTextColor(Color.parseColor(textColor))
//        binding.tvTitle.setTextColor(Color.parseColor(textColor))
//        binding.tvTitle.text = setTextTitle()
        binding.tvArticleVideo.text = textVideo
//        utit.logi("gg","VideoExplanationActivity 30 textVideo=$textVideo")
    }

}