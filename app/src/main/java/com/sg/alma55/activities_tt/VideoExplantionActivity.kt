package com.sg.alma55.activities_tt

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sg.alma55.databinding.ActivityVideoExplantionBinding

class VideoExplantionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoExplantionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVideoExplantionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawCurrentVideoComment()
    }
    private fun drawCurrentVideoComment() {
        val backGroundColor = "#0A174E"
       val textColor = "#F5D042"
        val  texti = "אין עדיין הערות על השיר הזה"

        binding.mainBackground.setBackgroundColor(Color.parseColor(backGroundColor))
        binding.tvArticleVideo.setTextColor(Color.parseColor(textColor))
        binding.tvTitle.setTextColor(Color.parseColor(textColor))
        binding.tvTitle.text = setTextTitle()
        binding.tvArticleVideo.text = texti
    }
    private fun setTextTitle(): String =
        "---------------------------------\n" +
                "הערות על קטע השיר הזה\n" +
                "--------------------------------- "
}