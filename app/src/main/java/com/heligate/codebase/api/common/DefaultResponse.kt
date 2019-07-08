package com.heligate.codebase.api.common

import com.squareup.moshi.Json

data class DefaultResponse(
    @Json(name = "hello")
    val hello: String
)