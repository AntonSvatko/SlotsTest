package com.test.slots

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.slots.data.ITEMS
import com.test.slots.ui.fragments.slots.SlotsViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


class SlotUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val viewModel = SlotsViewModel()


    @Test
    fun generationRandomTest() {
        val increment = 3
        assertEquals(viewModel.setRandom(increment).size, ITEMS.values().size * increment)
    }

    @Test
    fun getResultAndBetTest() {
        val coefficient = 50
        assertEquals(
            viewModel.getResult(coefficient, coefficient, coefficient),
            viewModel.currentBet.value?.times(coefficient)
        )
    }

    @Test
    fun getResultWithDifferentItems() {
        assertEquals(
            viewModel.getResult(50, 5, 10),
            0
        )
    }

    @Test
    fun testSpin() {
        assertEquals(
            viewModel.spin(),
            true
        )
    }

    @Test
    fun testSpinNoMoney() {
        repeat(50) {
            viewModel.spin()
        }
        assertEquals(
            viewModel.spin(),
            false
        )
    }
}