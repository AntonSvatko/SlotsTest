package com.test.slots.ui.fragments.slots

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.slots.data.ITEMS
import com.test.slots.databinding.FragmentSlotsBinding
import com.test.slots.ui.adapter.ScrollingSlotAdapter
import com.test.slots.ui.adapter.layoutManager.SpeedyLinearLayoutManager

@SuppressLint("SetTextI18n")
class SlotFragment : Fragment() {
    private lateinit var binding: FragmentSlotsBinding
    private val listOfBets = listOf(10, 20, 30)
    private var currentBet = listOfBets.first()

    private val adapter1 by lazy {
        ScrollingSlotAdapter().apply { submitList(ITEMS.values()) }
    }
    private val adapter2 by lazy {
        ScrollingSlotAdapter().apply { submitList(ITEMS.values()) }
    }
    private val adapter3 by lazy {
        ScrollingSlotAdapter().apply { submitList(ITEMS.values()) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSlotsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSlot1.setupRecyclerView(adapter1)
        binding.rvSlot2.setupRecyclerView(adapter2)
        binding.rvSlot3.setupRecyclerView(adapter3) {
            binding.isScrolling = false
        }
        binding.scoreBet.text = "$$currentBet"

        clicks()
    }


    private fun clicks() {
        binding.btnSpin.setOnClickListener {
            binding.rvSlot1.scrollRecyclerViewSlot(adapter1, 3)
            binding.rvSlot2.scrollRecyclerViewSlot(adapter2, 4)
            binding.rvSlot3.scrollRecyclerViewSlot(adapter3, 5)
            binding.isScrolling = true
        }

        binding.btnBetOne.setOnClickListener {
            binding.scoreBet.text = "$${nextBet()}"
        }
        binding.btnBetMax.setOnClickListener {
            binding.scoreBet.text = "$${maxBet()}"
        }
    }

    private fun nextBet(): Int {
        val currentBetIndex = listOfBets.indexOf(currentBet)
        currentBet = if (currentBetIndex == listOfBets.lastIndex)
            listOfBets.first()
        else
            listOfBets[currentBetIndex + 1]
        return currentBet
    }

    private fun maxBet(): Int {
        currentBet = listOfBets.last()
        return currentBet
    }

    private fun RecyclerView.setupRecyclerView(
        adapter: ScrollingSlotAdapter,
        callBack: (() -> Unit)? = null
    ) {
        layoutManager =
            SpeedyLinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false,
                callBack
            )
        this.adapter = adapter
    }

    private fun RecyclerView.scrollRecyclerViewSlot(adapter: ScrollingSlotAdapter, increment: Int) {
        val list = setRandom(increment)
        adapter.submitList(null)
        adapter.submitList(list)
        smoothScrollToPosition(list.lastIndex)
    }

    private fun setRandom(increment: Int): MutableList<ITEMS> =
        ITEMS.values().toMutableList().apply {
            times(increment)
            shuffle()
        }


    operator fun <T> MutableList<T>.times(increment: Int) {
        val len = this.size
        repeat(increment - 1) {
            this += this.subList(0, len)
        }
    }
}