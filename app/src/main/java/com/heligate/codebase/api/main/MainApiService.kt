package com.heligate.codebase.api.main

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET

interface MainApiService{
    @GET("/api/v1/employee/64644")
    fun getData(): Observable<Employee>
}