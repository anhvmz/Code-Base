package com.heligate.codebase.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel : ViewModel() {


    /**
     * Get the busy status (loading)
     *
     * @return Loading status
     */
    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }

    private val mCompositeDisposable = CompositeDisposable()

    /**
     * On view created
     */
    fun onViewCreated() {

    }

    /**
     * On destroy view
     */
    fun onDestroyView() {

    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    /**
     * Set the status of application is busy
     *
     * @param status Loading status
     */
    fun setIsLoading(status: Boolean) {
        isLoading.value = status
    }

    /**
     * Add Disposable to CompositeDisposable
     *
     * @param disposable Disposable
     */
    protected fun addCompositeDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

}
