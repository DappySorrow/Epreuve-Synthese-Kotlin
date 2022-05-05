package com.tp_apps.presentation.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.tp_apps.MainActivity
import com.tp_apps.R
import com.tp_apps.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    var counter = 0

    val timer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            binding.txvCounter.text = (++counter).toString()
            binding.pgbLoading.setProgress(counter, true)
        }

        override fun onFinish() {
            startActivity(MainActivity.newIntent(this@SplashActivity))
            this@SplashActivity.finish()
        }
    }

    //==========================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timer.start()

        binding.test.setOnClickListener() {
            timer.cancel()
            startActivity(MainActivity.newIntent(this))
            this.finish()
        }


    }
}