package com.marwa.mazadattask.presentation.main_categories

import androidx.lifecycle.MutableLiveData

object Item {
    val ItemLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}