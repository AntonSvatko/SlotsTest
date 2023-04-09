package com.test.slots.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.slots.R
import com.test.slots.data.ITEMS
import com.test.slots.data.ImageSlot
import com.test.slots.databinding.ItemSlotBinding


class ScrollingSlotAdapter :
    androidx.recyclerview.widget.ListAdapter<ITEMS, ScrollingSlotAdapter.ScoreHolder>(
        ScoreCallback()
    ) {
    inner class ScoreHolder(private val binding: ItemSlotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ITEMS) {
           binding.image.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreHolder {
        val binding: ItemSlotBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_slot,
            parent, false
        )
        return ScoreHolder(binding)
    }

    override fun onBindViewHolder(holder: ScoreHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun submitList(list: Array<ITEMS>) {
        super.submitList(list.toList())
    }
}

class ScoreCallback : DiffUtil.ItemCallback<ITEMS>() {
    override fun areItemsTheSame(oldItem: ITEMS, newItem: ITEMS): Boolean {
        return oldItem.value == newItem.value
    }

    override fun areContentsTheSame(oldItem: ITEMS, newItem: ITEMS): Boolean {
        return oldItem == newItem
    }
}