package com.example.teladoctesttask.ui.features.itunes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teladoctesttask.databinding.FragmentItunesBinding
import com.example.teladoctesttask.presentation.features.itunes.ITunesViewModel
import com.example.teladoctesttask.ui.base.BaseFragment
import com.example.teladoctesttask.ui.features.itunes.adapters.ITunesRecyclerViewAdapter
import com.example.teladoctesttask.utils.extensions.setOnTextChangeDebouncedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ITunesFragment : BaseFragment() {

    private val viewModel by viewModels<ITunesViewModel>()
    
    private var _binding: FragmentItunesBinding? = null
    private val binding get() = checkNotNull(_binding)

    private var rvAdapter: ITunesRecyclerViewAdapter? = ITunesRecyclerViewAdapter(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentItunesBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bindToLifecycle(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() = with(binding) {
        rvItems.layoutManager = LinearLayoutManager(requireContext())
        rvItems.adapter = rvAdapter
    }

    private fun initListeners() = with(binding) {
        layoutHelper.btnTryAgain.setOnClickListener { viewModel.retry() }
        etNameQuery.setOnTextChangeDebouncedListener { viewModel.onQueryChange(it) }
    }

    private fun initObservers() {
        viewModel.albumsData().observe(viewLifecycleOwner) {
            rvAdapter?.updateData(it)
        }
    }

    override fun showLoader() = with(binding) {
        layoutHelper.loadingGroup.isVisible = true
        contentGroup.isVisible = false
        layoutHelper.errorGroup.isVisible = false
    }

    override fun showContent() = with(binding) {
        layoutHelper.loadingGroup.isVisible = false
        contentGroup.isVisible = true
        layoutHelper.errorGroup.isVisible = false
    }

    override fun showError() = with(binding) {
        layoutHelper.loadingGroup.isVisible = false
        contentGroup.isVisible = false
        layoutHelper.errorGroup.isVisible = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rvAdapter = null
        _binding = null
    }
}