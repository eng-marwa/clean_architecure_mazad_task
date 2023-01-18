package com.marwa.mazadattask.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.marwa.mazadattask.BuildConfig
import com.marwa.mazadattask.app.MazadatApp
import com.marwa.mazadattask.data.datasource.remote.ApiService
import com.marwa.mazadattask.data.repositories.CategoriesRemoteDS
import com.marwa.mazadattask.data.repositories.CategoriesRemoteDSImp
import com.marwa.mazadattask.domain.repositories.CategoriesRepo
import com.marwa.mazadattask.domain.repositories.CategoriesRepoImp
import com.marwa.mazadattask.domain.usecases.CategoriesUseCases
import com.marwa.mazadattask.domain.usecases.OptionsUseCases
import com.marwa.mazadattask.domain.usecases.SubCategoriesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MazadatApp {
        return app as MazadatApp
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASED_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    private val READ_TIMEOUT = 30
    private val WRITE_TIMEOUT = 30
    private val CONNECTION_TIMEOUT = 10
    private val CACHE_SIZE_BYTES = 10 * 1024 * 1024L // 10 MB

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor(headerInterceptor)


        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            it.proceed(requestBuilder.build())
        }
    }


    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
    }


    @Provides
    @Singleton
    fun provideContext(application: MazadatApp): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRemoteCategoryDS(api: ApiService): CategoriesRemoteDS {
        return CategoriesRemoteDSImp(api)
    }


    @Provides
    @Singleton
    fun provideCategoryDSRepository(categoriesRemoteDS: CategoriesRemoteDS): CategoriesRepo {
        return CategoriesRepoImp(categoriesRemoteDS)
    }

    @Provides
    @Singleton
    fun provideCategoryUseCase(categoriesRepo: CategoriesRepo): CategoriesUseCases {
        return CategoriesUseCases(categoriesRepo)
    }

    @Provides
    @Singleton
    fun provideSubCategoryUseCase(categoriesRepo: CategoriesRepo): SubCategoriesUseCases {
        return SubCategoriesUseCases(categoriesRepo)
    }

    @Provides
    @Singleton
    fun provideOptionsUseCase(categoriesRepo: CategoriesRepo): OptionsUseCases {
        return OptionsUseCases(categoriesRepo)
    }

}