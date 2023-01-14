package com.marwa.mazadattask.data.model.options

import com.google.gson.annotations.SerializedName
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions


data class OptionsData (

  @SerializedName("id"          ) var id          : Int?               = null,
  @SerializedName("name"        ) var name        : String?            = null,
  @SerializedName("description" ) var description : String?            = null,
  @SerializedName("slug"        ) var slug        : String?            = null,
  @SerializedName("parent"      ) var parent      : String?            = null,
  @SerializedName("list"        ) var list        : Boolean?           = null,
  @SerializedName("type"        ) var type        : String?            = null,
  @SerializedName("value"       ) var value       : String?            = null,
  @SerializedName("other_value" ) var otherValue  : String?            = null,
  @SerializedName("options"     ) var options     : ArrayList<SubCategoryOptions> = arrayListOf()

)