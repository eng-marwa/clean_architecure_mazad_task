package com.marwa.mazadattask.data.model.sub_categiores

import com.google.gson.annotations.SerializedName


data class ResponseSubCategories (

  @SerializedName("code" ) var code : Int?            = null,
  @SerializedName("msg"  ) var msg  : String?         = null,
  @SerializedName("data" ) var data : ArrayList<SubCategoriesData> = arrayListOf()

)