package com.marwa.mazadattask.presentation.main_categories.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.marwa.mazadattask.app.BaseException
import com.marwa.mazadattask.data.model.main_categiores.ResponseCategories
import com.marwa.mazadattask.data.model.options.ResponseOptions
import com.marwa.mazadattask.data.model.sub_categiores.ResponseSubCategories
import com.marwa.mazadattask.domain.usecases.CategoriesUseCases
import com.marwa.mazadattask.domain.usecases.OptionsUseCases
import com.marwa.mazadattask.domain.usecases.SubCategoriesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUseCases: CategoriesUseCases,
    private val subCategoriesUseCases: SubCategoriesUseCases,
    private val optionsUseCases: OptionsUseCases
) :
    ViewModel() {
    val categoriesLiveData = MutableLiveData<Either<ResponseCategories, BaseException>>()
    val subCategoriesOptionsLiveData = MutableLiveData<Either<ResponseSubCategories, BaseException>>()
    val optionsLiveData = MutableLiveData<Either<ResponseOptions, BaseException>>()
    fun getCategories() {
        categoriesUseCases.invoke(onSuccess = {
            categoriesLiveData.value = Either.Left<ResponseCategories>(it)
        }, onError = {
            it?.let {
                categoriesLiveData.value = Either.Right<BaseException>(it)

            }
        })
    }

    fun getSubCategoriesOptions(id: Int) {
        subCategoriesUseCases.invoke(id, onSuccess = {
            subCategoriesOptionsLiveData.value = Either.Left<ResponseSubCategories>(it)
        }, onError = {
            it?.let {
                subCategoriesOptionsLiveData.value = Either.Right<BaseException>(it)

            }

        })
    }

    fun getOptions(id: Int) {
        optionsUseCases.invoke(id, onSuccess = {
            optionsLiveData.value = Either.Left<ResponseOptions>(it)
        }, onError = {
            it?.let {
                optionsLiveData.value = Either.Right<BaseException>(it)

            }
        })
    }



}