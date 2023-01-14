package com.marwa.mazadattask.data.model.main_categiores
import com.google.gson.annotations.SerializedName


data class AdsBanners(

    @SerializedName("img") var img: String? = null,
    @SerializedName("media_type") var mediaType: String? = null,
    @SerializedName("duration") var duration: Int? = null

)