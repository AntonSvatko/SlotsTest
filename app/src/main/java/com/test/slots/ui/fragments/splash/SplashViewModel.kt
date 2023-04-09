package com.test.slots.ui.fragments.splash

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    private val _progressLiveData = MutableLiveData<Int>()
    val progressLiveData: LiveData<Int>
        get() = _progressLiveData

    fun startProgress(progressTimeMillis: Long) {
        val oneTick = progressTimeMillis / 100

        object : CountDownTimer(progressTimeMillis, oneTick) {
            override fun onTick(millisUntilFinished: Long) {
                val progressInMillis = (progressTimeMillis - millisUntilFinished)
                _progressLiveData.value =
                    ((progressInMillis * MAX_PROGRESS) / progressTimeMillis).toInt()
            }

            override fun onFinish() {
                _progressLiveData.value = MAX_PROGRESS
            }

        }.start()
    }

    companion object {
        const val MAX_PROGRESS = 100
    }
}