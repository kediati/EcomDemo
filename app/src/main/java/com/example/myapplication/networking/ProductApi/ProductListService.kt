package com.example.myapplication.networking.ProductApi

import com.example.myapplication.model.Product
import com.example.myapplication.networking.RetrofitHelper
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

interface ProductListService {
    @GET("/products")
    suspend fun getProductList(): Response<List<Product>>
}
