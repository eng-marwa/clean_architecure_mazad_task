package com.marwa.mazadattask.data.model.sub_categiores

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubCategoryOptions (

  @SerializedName("id"     ) var id     : Int?     = null,
  @SerializedName("name"   ) var name   : String?  = null,
  @SerializedName("slug"   ) var slug   : String?  = null,
  @SerializedName("parent" ) var parent : Int?     = null,
  @SerializedName("child"  ) var child  : Boolean? = null


):Parcelable