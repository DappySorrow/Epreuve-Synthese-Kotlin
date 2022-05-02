package com.tp_apps.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tp_apps.R
import com.tp_apps.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}