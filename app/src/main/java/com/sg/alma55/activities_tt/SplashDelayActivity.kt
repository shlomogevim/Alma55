package com.sg.alma55.activities_tt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sg.alma55.R
import com.sg.alma55.activities.SplashActivity
import com.sg.alma55.databinding.ActivitySplashDelayBinding
import com.sg.alma55.utilities.Constants.SHARPREF_ALMA
import com.sg.alma55.utilities.Constants.SHARPREF_SPLASH_SCREEN_DELAY

class SplashDelayActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashDelayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashDelayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSplashDelay.setOnClickListener {
            val newSplashDelay = binding.etSlashDelay.text.toString().toInt()
            val pref = getSharedPreferences(SHARPREF_ALMA, Context.MODE_PRIVATE)
            pref.edit().putInt(SHARPREF_SPLASH_SCREEN_DELAY, newSplashDelay).apply()
            startActivity(Intent(this, SplashActivity::class.java))
        }
    }
}
