package com.test.slots.ui.fragments.slots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.slots.data.ITEMS
import com.test.slots.databinding.FragmentSlotsBinding
import com.test.slots.ui.adapter.ScrollingSlotAdapter
import com.test.slots.ui.adapter.layoutManager.SpeedyLinearLayoutManager

class SlotFragment : Fragment() {
    private lateinit var binding: FragmentSlotsBinding

    private val adapter by lazy { ScrollingSlotAdapter() }

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
        binding.recycler.layoutManager =
            SpeedyLinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycler.adapter = adapter


        binding.btn.setOnClickListener {
            adapter.submitList(ITEMS.values())
            binding.recycler.smoothScrollToPosition(3)
        }
    }
}