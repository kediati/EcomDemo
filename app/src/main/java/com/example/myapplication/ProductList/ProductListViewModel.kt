package com.example.myapplication.ProductList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val getProductsUseCase: ProductRepository): ViewModel() {


    val uiState = MutableLiveData<ProductListState>()
    val productListLiveData = MutableLiveData<List<Product>>(emptyList<Product>())

    init {
        loadProductList()
    }

    private fun loadProductList() {
        uiState.value = ProductListState.LOADING
        /*getProductsUseCase.getProductList {
            productListLiveData.value = it
            uiState.value = ProductListState.LOADED
        }*/

        viewModelScope.launch(Dispatchers.Main.immediate) {
            try {
                val list = getProductsUseCase.getProductList()
                productListLiveData.value = list
                uiState.value = ProductListState.LOADED
            }catch (e: Exception){

            }
        }
    }

}