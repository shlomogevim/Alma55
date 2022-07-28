package com.sg.alma55.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sg.alma55.databinding.ActivityGetNextPostBinding
import com.sg.alma55.utilities.Constants.SHARPREF_ALMA
import com.sg.alma55.utilities.Constants.SHARPREF_CURRENT_POST_NUM

class GetNextPost : AppCompatActivity() {
    private lateinit var binding:ActivityGetNextPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGetNextPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPassPost.setOnClickListener {
            val newPostNum = binding.etPassNum.text.toString().toInt()
            val pref = getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
            pref.edit().putInt(SHARPREF_CURRENT_POST_NUM, newPostNum).apply()
            startActivity(Intent(this, MainActivity::class.java))
            // finish()
        }
    }


}