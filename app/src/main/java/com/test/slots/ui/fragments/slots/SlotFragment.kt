package com.test.slots.ui.fragments.slots

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.slots.data.ITEMS
import com.test.slots.databinding.FragmentSlotsBinding
import com.test.slots.ui.adapter.ScrollingSlotAdapter
import com.test.slots.ui.adapter.layoutManager.SpeedyLinearLayoutManager

@SuppressLint("SetTextI18n")
class SlotFragment : Fragment() {
    private lateinit var binding: FragmentSlotsBinding
    private val viewModel: SlotsViewModel by viewModels()

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
            val result = viewModel.getResult()
            binding.scoreWin.text = "$$result"
        }

        viewModel.credit.observe(viewLifecycleOwner) {
            binding.scoreCredit.text = "$$it"
        }

        viewModel.currentBet.observe(viewLifecycleOwner) {
            binding.scoreBet.text = "$$it"
        }

        clicks()
    }


    private fun clicks() {
        binding.btnSpin.setOnClickListener {
            spin()
        }

        binding.btnBetOne.setOnClickListener {
            viewModel.nextBet()
        }
        binding.btnBetMax.setOnClickListener {
            viewModel.maxBet()
        }
    }

    private fun spin() {
        viewModel.finishItemCoef1 =
            binding.rvSlot1.scrollRecyclerViewSlot(adapter1, 3).coefficient
        viewModel.finishItemCoef2 =
            binding.rvSlot2.scrollRecyclerViewSlot(adapter2, 4).coefficient
        viewModel.finishItemCoef3 =
            binding.rvSlot3.scrollRecyclerViewSlot(adapter3, 5).coefficient
        binding.isScrolling = true
        viewModel.spin()
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

    private fun RecyclerView.scrollRecyclerViewSlot(
        adapter: ScrollingSlotAdapter,
        increment: Int
    ): ITEMS {
        val list = viewModel.setRandom(increment)
        adapter.submitList(null)
        adapter.submitList(list)
        smoothScrollToPosition(list.lastIndex)
        return list.last()
    }
}