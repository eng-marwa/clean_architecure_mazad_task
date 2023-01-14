package com.marwa.mazadattask.data.model.main_categiores

import com.google.gson.annotations.SerializedName


data class Statistics (

  @SerializedName("auctions" ) var auctions : Int? = null,
  @SerializedName("users"    ) var users    : Int? = null,
  @SerializedName("products" ) var products : Int? = null

)