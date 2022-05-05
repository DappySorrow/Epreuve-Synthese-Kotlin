package com.tp_apps.presentation.ui.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.viewbinding.library.activity.viewBinding
import android.viewbinding.library.fragment.viewBinding
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.tp_apps.MainActivity
import com.tp_apps.R
import com.tp_apps.core.Constants.TIMER_INTERVAL
import com.tp_apps.core.Constants.TIMER_MAX
import com.tp_apps.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    //==========================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.counter.observe(this) {
            binding.txvCounter.text = getString(R.string.timer, (it).toString())
            binding.pgbLoading.setProgress(it, true)
        }

        viewModel.isTimerDone.observe(this) {
            if (it) {
                startActivity(MainActivity.newIntent(this))
                this.finish()
            }
        }


/*
        binding.test.setOnClickListener() {
            timer.cancel()
            startActivity(MainActivity.newIntent(this))
            this.finish()
        }
*/

    }
}