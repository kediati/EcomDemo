package com.example.myapplication.dependencyinjection

import com.example.myapplication.networking.ProductApi.ProductListService
import com.example.myapplication.networking.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun retrofit(): Retrofit = RetrofitHelper.retrofit

    @Singleton
    @Provides
    fun productListService(retrofit: Retrofit) = retrofit.create(ProductListService::class.java)

}