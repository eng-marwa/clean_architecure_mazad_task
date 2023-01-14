package com.marwa.mazadattask.data.repositories

import com.marwa.mazadattask.data.datasource.remote.ApiResponseCallbacks
import com.marwa.mazadattask.data.datasource.remote.ApiService
import com.marwa.mazadattask.data.datasource.remote.BaseApiProvider
import com.marwa.mazadattask.data.model.main_categiores.ResponseCategories
import com.marwa.mazadattask.data.model.options.ResponseOptions
import com.marwa.mazadattask.data.model.sub_categiores.ResponseSubCategories
import com.marwa.mazadattask.util.ui
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

interface CategoriesRemoteDS {
    fun getCategories(apiResponseCallbacks: ApiResponseCallbacks<ResponseCategories>)
    fun getSubCategories(
        id: Int,
        apiResponseCallbacks: ApiResponseCallbacks<ResponseSubCategories>
    )

    fun getOptions(id: Int, apiResponseCallbacks: ApiResponseCallbacks<ResponseOptions>)

}

class CategoriesRemoteDSImp @Inject constructor(private val apiService: ApiService) :
    BaseApiProvider(),
    CategoriesRemoteDS {
    override fun getCategories(apiResponseCallbacks: ApiResponseCallbacks<ResponseCategories>) {
        ui {
            apiRequest({ apiService.getMainCategories() }, apiResponseCallbacks)
        }
    }

    override fun getSubCategories(
        id: Int,
        apiResponseCallbacks: ApiResponseCallbacks<ResponseSubCategories>
    ) {
        ui {
            apiRequest({ apiService.getSubCategories(id) }, apiResponseCallbacks)
        }
    }

    override fun getOptions(
        id: Int,
        apiResponseCallbacks: ApiResponseCallbacks<ResponseOptions>
    ) {
        ui {
            apiRequest({ apiService.getOptions(id) }, apiResponseCallbacks)
        }
    }


}