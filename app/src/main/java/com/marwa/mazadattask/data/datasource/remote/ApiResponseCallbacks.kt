package com.marwa.mazadattask.data.datasource.remote

import com.marwa.mazadattask.app.BaseException


data class ApiResponseCallbacks<T>(
    val onResult: (response: T?) -> Unit,
    val onError: (error: BaseException) -> Unit,
)
