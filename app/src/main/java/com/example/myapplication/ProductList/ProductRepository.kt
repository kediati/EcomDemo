package com.example.myapplication.ProductList

import com.example.myapplication.model.Product
import com.example.myapplication.networking.ProductApi.ProductListService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class ProductRepository @Inject constructor(val productListService: ProductListService) {
    suspend fun getProductList(): List<Product> {
        delay(1000)
        return withContext(Dispatchers.IO) {
            try {
                val response = productListService.getProductList()
                if(response.isSuccessful) {
                    response.body() ?: emptyList<Product>()
                } else {
                    emptyList<Product>() // return empty list if the API request is not successful
                }
            }catch (e: HttpException) {
                emptyList<Product>()
            } catch (e: Throwable) {
                emptyList<Product>()
            }
        }
    }
}