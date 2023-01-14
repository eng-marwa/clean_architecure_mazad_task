package com.marwa.mazadattask.data.model.main_categiores

import com.google.gson.annotations.SerializedName


data class ResponseCategories(

    @SerializedName("code") var code: Int? = null,
    @SerializedName("msg") var msg: String? = null,
    @SerializedName("data") var data: CategoriesData = CategoriesData()

)