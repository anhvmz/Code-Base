package com.heligate.codebase.base

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AlertDialog
import android.view.inputmethod.InputMethodManager
import butterknife.ButterKnife
import com.heligate.codebase.R
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : DaggerAppCompatActivity() {

    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null

    abstract val bindingVariable: Int

    /**
     * Get ViewModel with this activity
     *
     * @return ViewModel instance
     */
    abstract val viewModel: V

    @get:LayoutRes
    abstract val layoutId: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mViewModel = viewModel
        this.mViewModel?.onViewCreated()
        performDataBinding()
        ButterKnife.bind(this)
    }

    /**
     * Init the loading view for this activity.
     *
     *
     * When the method called, the Activity when auto show/hide loading dialog base
     * on ViewModel busy status
     */
    fun initLoadingView() {
        mViewModel?.isLoading?.observe(this, Observer { value ->
            value?.let {
                if (it) {
                    LoadingDialogFragment.show(supportFragmentManager)
                } else {
                    LoadingDialogFragment.dismiss(supportFragmentManager)
                }
            }
        })
    }

    override fun onDestroy() {
        this.mViewModel!!.onDestroyView()
        super.onDestroy()
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
    }


    protected fun showAlertDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setTitle(R.string.app_name)
            .setPositiveButton(R.string.button_ok) { dialog, which ->

            }
        val dialog = builder.create()
        dialog.show()
    }

    /**
     * Hide virtual keyboard on request
     */
    protected fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}
