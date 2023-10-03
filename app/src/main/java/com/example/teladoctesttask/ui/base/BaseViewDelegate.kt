package com.example.teladoctesttask.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import com.example.teladoctesttask.presentation.base.BaseViewModel
import com.example.teladoctesttask.presentation.base.State

interface BaseView : LifecycleOwner, ViewModelStoreOwner {

    fun showLoader()
    fun showError()
    fun showContent()
}

class BaseViewDelegate {

    fun listenStates(baseView: BaseView, viewModel: BaseViewModel) {
        viewModel.stateLiveData.observe(baseView) {
            when (it) {
                is State.Loading -> baseView.showLoader()
                is State.Content -> baseView.showContent()
                is State.Error -> baseView.showError()
            }
        }
    }

    fun bindViewModelToLifecycle(baseView: BaseView, viewModel: BaseViewModel) {
        baseView.lifecycle.addObserver(viewModel)
    }
}