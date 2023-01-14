package com.marwa.mazadattask.domain.repositories

import com.marwa.mazadattask.data.datasource.remote.ApiResponseCallbacks
import com.marwa.mazadattask.data.model.main_categiores.ResponseCategories
import com.marwa.mazadattask.data.model.options.ResponseOptions
import com.marwa.mazadattask.data.model.sub_categiores.ResponseSubCategories
import com.marwa.mazadattask.data.repositories.CategoriesRemoteDS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CategoriesRepo {
     fun getCategories(apiResponseCallbacks: ApiResponseCallbacks<ResponseCategories>)
     fun getSubCategories(
        id: Int,
        apiResponseCallbacks: ApiResponseCallbacks<ResponseSubCategories>
    )

     fun getOptions(id: Int, apiResponseCallbacks: ApiResponseCallbacks<ResponseOptions>)

}

class CategoriesRepoImp @Inject constructor(private val categoriesRemoteDS: CategoriesRemoteDS) :
    CategoriesRepo {
    override  fun getCategories(apiResponseCallbacks: ApiResponseCallbacks<ResponseCategories>) =
        categoriesRemoteDS.getCategories(apiResponseCallbacks)

    override  fun getSubCategories(
        id: Int,
        apiResponseCallbacks: ApiResponseCallbacks<ResponseSubCategories>
    ) = categoriesRemoteDS.getSubCategories(id, apiResponseCallbacks)

    override  fun getOptions(
        id: Int,
        apiResponseCallbacks: ApiResponseCallbacks<ResponseOptions>
    ) = categoriesRemoteDS.getOptions(id, apiResponseCallbacks)


}