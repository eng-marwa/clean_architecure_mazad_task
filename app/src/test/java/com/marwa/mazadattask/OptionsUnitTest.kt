package com.marwa.mazadattask

import com.google.gson.GsonBuilder
import com.marwa.mazadattask.data.datasource.remote.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class OptionsUnitTest {
    private lateinit var retrofit: Retrofit

    @Before
    fun init() {

        retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASED_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Test
    fun testCategoriesList() = runBlocking {
        val service = retrofit.create(ApiService::class.java)
        val response = service.getOptions(53)
        val errorBody = response.errorBody()
        assert(errorBody == null)
        val responseWrapper = response.body()
        assert(responseWrapper != null)
        assert(response.code() == 200)
    }
}
