package com.test.slots.ui.fragments.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.slots.databinding.FragmentSplashBinding
import com.test.slots.ui.fragments.splash.SplashViewModel.Companion.MAX_PROGRESS

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startProgress(3000)
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.progress = it
            if (it == MAX_PROGRESS)
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMenuFragment())
        }

    }
}