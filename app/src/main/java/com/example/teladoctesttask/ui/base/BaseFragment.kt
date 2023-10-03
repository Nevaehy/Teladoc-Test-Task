package com.example.teladoctesttask.ui.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.teladoctesttask.presentation.base.BaseViewModel

abstract class BaseFragment : Fragment, BaseView {
    constructor() : super()
    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    private val delegate = BaseViewDelegate()

    internal fun bindToLifecycle(viewModel: BaseViewModel) {
        delegate.bindViewModelToLifecycle(this, viewModel)
        delegate.listenStates(this, viewModel)
    }
}