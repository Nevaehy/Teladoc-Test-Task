package com.example.teladoctesttask.ui.features.poems

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teladoctesttask.R
import com.example.teladoctesttask.databinding.FragmentPoemsBinding
import com.example.teladoctesttask.presentation.features.poems.PoemsViewModel
import com.example.teladoctesttask.ui.base.BaseFragment
import com.example.teladoctesttask.ui.features.poems.adapters.PoemsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PoemsFragment : BaseFragment() {

    private val viewModel by viewModels<PoemsViewModel>()
    
    private var _binding: FragmentPoemsBinding? = null
    private val binding get() = checkNotNull(_binding)

    private var rvAdapter: PoemsRecyclerViewAdapter? = PoemsRecyclerViewAdapter(listOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentPoemsBinding.inflate(inflater, container, false).also {
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
        ivFreqSort.setOnClickListener { viewModel.onSortClick(PoemsViewModel.Sort.FREQUENCY) }
        ivAlphabSort.setOnClickListener { viewModel.onSortClick(PoemsViewModel.Sort.ALPHABET) }
        ivLengthSort.setOnClickListener { viewModel.onSortClick(PoemsViewModel.Sort.WORD_LENGTH) }
    }

    private fun initObservers() {
        viewModel.poemData().observe(viewLifecycleOwner) {
            rvAdapter?.updateData(it)
        }

        viewModel.lastSort().observe(viewLifecycleOwner) {
            updateSortViews(it)
        }
    }

    private fun updateSortViews(sort: PoemsViewModel.Sort) = with(binding) {
        disableAllSorts()
        val highlightColor = ContextCompat.getColor(requireContext(), R.color.secondaryColor)

        when (sort) {
            PoemsViewModel.Sort.FREQUENCY -> ivFreqSort.setColorFilter(highlightColor, android.graphics.PorterDuff.Mode.SRC_IN)
            PoemsViewModel.Sort.ALPHABET -> ivAlphabSort.setColorFilter(highlightColor, android.graphics.PorterDuff.Mode.SRC_IN)
            PoemsViewModel.Sort.WORD_LENGTH -> ivLengthSort.setColorFilter(highlightColor, android.graphics.PorterDuff.Mode.SRC_IN)
            else -> {
                // ignore
            }
        }

    }

    private fun disableAllSorts() = with(binding) {
        val defaultColor = ContextCompat.getColor(requireContext(), R.color.white)

        ivFreqSort.setColorFilter(defaultColor, android.graphics.PorterDuff.Mode.SRC_IN)
        ivAlphabSort.setColorFilter(defaultColor, android.graphics.PorterDuff.Mode.SRC_IN)
        ivLengthSort.setColorFilter(defaultColor, android.graphics.PorterDuff.Mode.SRC_IN)
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