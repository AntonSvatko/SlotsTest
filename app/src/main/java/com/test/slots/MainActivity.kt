package com.test.slots

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.slots.data.ITEMS
import com.test.slots.databinding.ActivityMainBinding
import com.test.slots.ui.adapter.ScrollingSlotAdapter
import com.test.slots.ui.adapter.layoutManager.SpeedyLinearLayoutManager

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }
    private val adapter by lazy { ScrollingSlotAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recycler.layoutManager =
            SpeedyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycler.adapter = adapter


        binding.btn.setOnClickListener {
            adapter.submitList(ITEMS.values())
            binding.recycler.smoothScrollToPosition(3)
        }
    }
}