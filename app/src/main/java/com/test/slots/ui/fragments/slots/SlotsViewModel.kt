package com.test.slots.ui.fragments.slots

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.slots.data.ITEMS
import com.test.slots.utils.times

class SlotsViewModel : ViewModel() {
    private val listOfBets = listOf(10, 20, 30)
    var finishItemCoef1: Int = 0
    var finishItemCoef2: Int = 0
    var finishItemCoef3: Int = 0

    private var _currentBet = MutableLiveData(listOfBets.first())
    val currentBet: LiveData<Int>
        get() = _currentBet

    private var _credit = MutableLiveData(500)
    val credit: LiveData<Int>
        get() = _credit

    fun nextBet() {
        val currentBetIndex = listOfBets.indexOf(currentBet.value)
        _currentBet.value = if (currentBetIndex == listOfBets.lastIndex)
            listOfBets.first()
        else
            listOfBets[currentBetIndex + 1]
    }

    fun maxBet() {
        _currentBet.value = listOfBets.last()
    }

    fun spin() {
        _credit.value = _credit.value?.minus(_currentBet.value ?: 0)
    }

    fun setRandom(increment: Int): MutableList<ITEMS> =
        ITEMS.values().toMutableList().apply {
            times(increment)
            shuffle()
        }

    fun getResult(
        finishItemCoef1: Int = this.finishItemCoef1,
        finishItemCoef2: Int = this.finishItemCoef2,
        finishItemCoef3: Int = this.finishItemCoef3
    ): Int {
        val countEqualsItems = setOf(finishItemCoef1, finishItemCoef2, finishItemCoef3).size
        val result = if (countEqualsItems == 1)
            finishItemCoef1 * (_currentBet.value ?: 0)
        else 0

        _credit.value = credit.value?.plus(result)
        return result
    }
}