package com.heligate.codebase.ui

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.heligate.codebase.BR
import com.heligate.codebase.R
import com.heligate.codebase.base.BaseActivity
import com.heligate.codebase.databinding.ActivityMainBinding
import com.heligate.codebase.di.ViewModelFactory
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    @Inject
    lateinit var mMainViewModelFactory: ViewModelFactory

    private var mLoginViewModel: MainActivityViewModel? = null
    private var mActivityMainBinding: ActivityMainBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: MainActivityViewModel
        get() = ViewModelProviders.of(this as AppCompatActivity, mMainViewModelFactory).get(MainActivityViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.activity_main


}
