package com.example.teladoctesttask.ui.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.teladoctesttask.R
import com.example.teladoctesttask.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentMenuBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnPoems.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_poemsFragment) }
        binding.btnITunes.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_iTunesFragment) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}