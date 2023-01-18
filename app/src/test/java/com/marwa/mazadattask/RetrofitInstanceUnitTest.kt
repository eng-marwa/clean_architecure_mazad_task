package com.marwa.mazadattask

import com.google.gson.GsonBuilder
import org.junit.Test

import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RetrofitInstanceUnitTest {
    private lateinit var retrofit:Retrofit
    @Before
    fun init() {
        retrofit=Retrofit.Builder().baseUrl(BuildConfig.BASED_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    }
    @Test
    fun testRetrofitInstance() {
        assert(retrofit.baseUrl().toUrl().toString() == BuildConfig.BASED_URL)
    }
}