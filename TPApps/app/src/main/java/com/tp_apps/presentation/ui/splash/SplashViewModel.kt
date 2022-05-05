package com.tp_apps.presentation.ui.splash

import android.os.CountDownTimer
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp_apps.MainActivity
import com.tp_apps.R
import com.tp_apps.core.Constants
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    //LiveData Int
    var _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> get() = _counter

    //LiveData Bool
    var _isTimerDone = MutableLiveData<Boolean>()
    val isTimerDone: LiveData<Boolean> get() = _isTimerDone

    //var timerDone = false

    private val timer = object: CountDownTimer(Constants.TIMER_MAX, Constants.TIMER_INTERVAL) {

        override fun onTick(millisUntilFinished: Long) {
            if (_counter.value == null){
                _counter.value = 0
            }
            _counter.value = counter.value!! + 1
        }

        override fun onFinish() {
            _isTimerDone.value = true
        }
    }

    init {
        timer.start()
    }

}