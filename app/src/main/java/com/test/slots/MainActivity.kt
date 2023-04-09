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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}