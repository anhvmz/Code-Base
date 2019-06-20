package com.heligate.codebase.api.main

import com.squareup.moshi.Json

data class Employee(
    @Json(name = "id")
    val id: String?
)