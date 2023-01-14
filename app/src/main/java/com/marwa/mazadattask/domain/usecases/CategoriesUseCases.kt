package com.marwa.mazadattask.domain.usecases

import com.marwa.mazadattask.app.BaseException
import com.marwa.mazadattask.data.datasource.remote.ApiResponseCallbacks
import com.marwa.mazadattask.data.model.main_categiores.ResponseCategories
import com.marwa.mazadattask.domain.repositories.CategoriesRepo

class CategoriesUseCases(private val categoriesRepo: CategoriesRepo) {
     fun invoke(
        onSuccess: (ResponseCategories) -> Unit,
        onError: (BaseException?) -> Unit,
    ) {
        categoriesRepo.getCategories(ApiResponseCallbacks(onResult = {
            it?.let { onSuccess(it) }
        }, onError = {
            onError(it)
        }))
    }
}