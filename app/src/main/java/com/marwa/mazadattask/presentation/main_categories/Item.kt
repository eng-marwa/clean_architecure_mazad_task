package com.marwa.mazadattask.presentation.main_categories

import androidx.lifecycle.MutableLiveData
import com.marwa.mazadattask.data.model.main_categiores.Categories
import com.marwa.mazadattask.data.model.main_categiores.Children
import com.marwa.mazadattask.data.model.options.OptionsData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoriesData
import com.marwa.mazadattask.data.model.sub_categiores.SubCategoryOptions

object Item {


    val ItemLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val SelectedItemLiveData: MutableLiveData<Categories> by lazy {
        MutableLiveData<Categories>()
    }
    val SelectedSubItemLiveData: MutableLiveData<Children> by lazy {
        MutableLiveData<Children>()
    }

    val SelectedSubCategoriesLiveData: MutableLiveData<Pair<SubCategoriesData, Int>> by lazy {
        MutableLiveData<Pair<SubCategoriesData, Int>>()
    }

    val SelectedSubCategoryOptionLiveData: MutableLiveData<Pair<SubCategoryOptions, Int>> by lazy {
        MutableLiveData<Pair<SubCategoryOptions, Int>>()
    }
    val OptionsItemsLiveData: MutableLiveData<Pair<OptionsData,Int>> by lazy {
        MutableLiveData<Pair<OptionsData,Int>>()
    }

    val ModalOptionsItemsLiveData: MutableLiveData<SubCategoryOptions> by lazy {
        MutableLiveData<SubCategoryOptions>()
    }
    val TypedOptionsItemsLiveData: MutableLiveData<SubCategoryOptions> by lazy {
        MutableLiveData<SubCategoryOptions>()
    }
    val SelectedTypeLiveData: MutableLiveData<Pair<OptionsData,Int>> by lazy {
        MutableLiveData<Pair<OptionsData,Int>>()
    }


}