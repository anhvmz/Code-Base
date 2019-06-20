package com.heligate.codebase.ui

import android.arch.lifecycle.MutableLiveData
import com.heligate.codebase.base.BaseViewModel
import com.heligate.codebase.repository.MainRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var mainRepository: MainRepository

    var txtValue = MutableLiveData<String>()

    init {
        txtValue.value = "1"
    }
}