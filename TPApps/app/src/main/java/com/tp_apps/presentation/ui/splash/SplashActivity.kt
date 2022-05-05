package com.tp_apps.presentation.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.tp_apps.MainActivity
import com.tp_apps.R
import com.tp_apps.core.Constants.TIMER_INTERVAL
import com.tp_apps.core.Constants.TIMER_MAX
import com.tp_apps.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    var counter = 0

    private val timer = object: CountDownTimer(TIMER_MAX, TIMER_INTERVAL) {
        override fun onTick(millisUntilFinished: Long) {
            binding.txvCounter.text = getString(R.string.timer, (++counter).toString())
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