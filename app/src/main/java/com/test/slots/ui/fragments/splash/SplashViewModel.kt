package com.test.slots.ui.fragments.splash

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _progressLiveData = MutableLiveData<Int>()
    val progressLiveData: LiveData<Int>
        get() = _progressLiveData

    private val _finishLiveData = MutableLiveData<Boolean>()
    val finishLiveData: LiveData<Boolean>
        get() = _finishLiveData

    fun startProgress(progressTimeMillis: Long) {
        val oneTick = progressTimeMillis / 100

        object : CountDownTimer(progressTimeMillis, oneTick) {
            override fun onTick(millisUntilFinished: Long) {
                _progressLiveData.value = (_progressLiveData.value ?: 0) + 1
            }

            override fun onFinish() {
                _finishLiveData.postValue(true)
            }

        }
    }

    companion object {
        private const val ONE_SEC = 1000L
    }
}