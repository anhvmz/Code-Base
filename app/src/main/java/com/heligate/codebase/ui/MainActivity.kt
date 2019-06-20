package com.heligate.codebase.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.OnClick
import com.heligate.codebase.BR
import com.heligate.codebase.R
import com.heligate.codebase.base.BaseActivity
import com.heligate.codebase.databinding.ActivityMainBinding
import com.heligate.codebase.di.ViewModelFactory
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    @Inject
    lateinit var mMainViewModelFactory: ViewModelFactory

    private lateinit var mLoginViewModel: MainActivityViewModel
    private var mActivityMainBinding: ActivityMainBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: MainActivityViewModel
        get() {
            mLoginViewModel = ViewModelProviders.of(this as AppCompatActivity, mMainViewModelFactory).get(MainActivityViewModel::class.java)
            return mLoginViewModel
        }
    override val layoutId: Int
        get() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = viewDataBinding
        initLoadingView()
        mLoginViewModel.txtValue.observe(this, Observer {
            mActivityMainBinding?.txtHello?.text = it ?: "0"
        })
    }

    @OnClick(R.id.txt_hello)
    fun onClickHello() {
//        mLoginViewModel.isLoading.value = true
//        Handler().postDelayed({
//            mLoginViewModel.isLoading.value = false
//
//        }, 3000)
        mLoginViewModel.txtValue.value = if (mActivityMainBinding?.txtHello?.text == "1") "2" else "1"
    }
}

