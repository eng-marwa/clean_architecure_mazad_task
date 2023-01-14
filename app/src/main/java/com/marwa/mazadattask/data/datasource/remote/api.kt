package com.marwa.mazadattask.data.datasource.remote

import com.marwa.mazadattask.data.model.main_categiores.ResponseCategories
import com.marwa.mazadattask.data.model.options.ResponseOptions
import com.marwa.mazadattask.data.model.sub_categiores.ResponseSubCategories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("get_all_cats")
    suspend fun getMainCategories(): Response<ResponseCategories>

    @GET("properties")
    suspend fun getSubCategories(@Query("cat") cadId: Int): Response<ResponseSubCategories>

    @GET("get-options-child/{optionId}")
    suspend fun getOptions(@Path("optionId") optionId: Int): Response<ResponseOptions>
}