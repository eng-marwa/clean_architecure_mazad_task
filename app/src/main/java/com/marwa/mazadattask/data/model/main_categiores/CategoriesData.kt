package com.marwa.mazadattask.data.model.main_categiores

import com.google.gson.annotations.SerializedName


data class CategoriesData(

    @SerializedName("categories") var categories: ArrayList<Categories> = arrayListOf(),
    @SerializedName("statistics") var statistics: Statistics? = Statistics(),
    @SerializedName("ads_banners") var adsBanners: ArrayList<AdsBanners> = arrayListOf(),
    @SerializedName("ios_version") var iosVersion: String? = null,
    @SerializedName("google_version") var googleVersion: String? = null,
    @SerializedName("huawei_version") var huaweiVersion: String? = null

)