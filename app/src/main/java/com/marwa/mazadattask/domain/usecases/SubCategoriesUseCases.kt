package com.marwa.mazadattask.domain.usecases

import com.marwa.mazadattask.app.BaseException
import com.marwa.mazadattask.data.datasource.remote.ApiResponseCallbacks
import com.marwa.mazadattask.data.model.main_categiores.ResponseCategories
import com.marwa.mazadattask.data.model.sub_categiores.ResponseSubCategories
import com.marwa.mazadattask.domain.repositories.CategoriesRepo

class SubCategoriesUseCases(private val categoriesRepo: CategoriesRepo) {
     fun invoke(id:Int,
        onSuccess: (ResponseSubCategories) -> Unit,
        onError: (BaseException?) -> Unit,
    ) {
        categoriesRepo.getSubCategories(id,ApiResponseCallbacks(onResult = {
            it?.let { onSuccess(it) }
        }, onError = {
            onError(it)
        }))
    }
}