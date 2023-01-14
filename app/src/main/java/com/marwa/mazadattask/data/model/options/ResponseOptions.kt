package com.marwa.mazadattask.data.model.options

import com.google.gson.annotations.SerializedName


data class ResponseOptions (

  @SerializedName("code" ) var code : Int?            = null,
  @SerializedName("msg"  ) var msg  : String?         = null,
  @SerializedName("data" ) var data : ArrayList<OptionsData> = arrayListOf()

)