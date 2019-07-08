package com.heligate.codebase.base

import android.databinding.ViewDataBinding
import butterknife.Unbinder
import dagger.android.support.DaggerFragment

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : DaggerFragment() {
    private var unbinder: Unbinder? = null
}