package com.heligate.codebase.repository

import com.heligate.codebase.api.common.DefaultResponse
import com.heligate.codebase.api.main.MainApiService
import io.reactivex.Observable

class MainRepository constructor(private val mainApiService: MainApiService) {
    fun getData(): Observable<DefaultResponse> {
        return mainApiService.getData()
    }
}