package com.tp_apps.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tp_apps.MainActivity
import com.tp_apps.R
import com.tp_apps.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.test.setOnClickListener() {
            startActivity(MainActivity.newIntent(this))
        }
    }
}