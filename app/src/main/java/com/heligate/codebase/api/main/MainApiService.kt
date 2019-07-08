package com.heligate.codebase.api.main

import com.heligate.codebase.api.common.DefaultResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MainApiService {
    @GET("/5185415ba171ea3a00704eed")
    fun getData(): Observable<DefaultResponse>
}