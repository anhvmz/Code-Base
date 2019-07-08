package com.heligate.codebase.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.OnClick
import com.heligate.codebase.BR
import com.heligate.codebase.R
import com.heligate.codebase.base.BaseActivity
import com.heligate.codebase.databinding.ActivityMainBinding
import com.heligate.codebase.di.ViewModelFactory
import com.squareup.moshi.Moshi
import javax.inject.Inject




class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    @Inject
    lateinit var mMainViewModelFactory: ViewModelFactory

    @Inject
    lateinit var moshi: Moshi

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
//        mLoginViewModel.txtValue.observe(this, Observer {
//            mActivityMainBinding?.txtHello?.text = it ?: "0"
//        })

    }

    @OnClick(R.id.txt_hello)
    fun onClickHello() {
//        mLoginViewModel.isLoading.value = true
//        Handler().postDelayed({
//            mLoginViewModel.isLoading.value = false
//
//        }, 3000)
//        mLoginViewModel.txtValue.value = if (mActivityMainBinding?.txtHello?.text == "1") "2" else "1"
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=21.009931,105.769327&daddr=21.036609,105.789691"))
//        val gmmIntentUri = Uri.parse("google.navigation:q=21.036609,105.789691")
//        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
//        mapIntent.setPackage("com.google.android.apps.maps")
//        startActivity(mapIntent)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&origin=18.519513,73.868315&destination=18.518496,73.879259&waypoints=18.520561,73.872435|18.519254,73.876614|18.52152,73.877327|18.52019,73.879935&travelmode=driving"))

        startActivity(intent)
    }
}

