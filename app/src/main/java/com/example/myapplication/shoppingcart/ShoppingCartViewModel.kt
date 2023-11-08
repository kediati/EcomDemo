package com.example.myapplication.shoppingcart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoppingCartViewModel() : ViewModel() {
    val viewStateLiveData = MutableLiveData<ShoppingCartState>()
    val cartItemListLiveData = MutableLiveData<List<CartItem>>()
    val totalAmountLiveData = MutableLiveData<String>()

    init {
        cartItemListLiveData.value=ShoppingCart.cartItemList
        totalAmountLiveData.value= ShoppingCart.total
        updateScreenState()
    }

    private fun updateScreenState() {
        if (ShoppingCart.getSize() > 0) {
            if (viewStateLiveData.value != ShoppingCartState.FILLED_CART)
                viewStateLiveData.value = ShoppingCartState.FILLED_CART
        } else {
            if (viewStateLiveData.value != ShoppingCartState.EMPTY_CART)
                viewStateLiveData.value = ShoppingCartState.EMPTY_CART
        }
    }

    fun onItemCountChange(cartQuantityEvent: CartQuantityEvent) {
        //update the shopping cart
        when (cartQuantityEvent.cartQuantityAction) {
            CartQuantityAction.INCREMENT -> ShoppingCart.incrementItemQuantity(cartQuantityEvent.productId)
            CartQuantityAction.DECREMENT -> ShoppingCart.decrementItemQuantity(cartQuantityEvent.productId)
        }
        //update the views (recycler and total)
        cartItemListLiveData.value = ShoppingCart.cartItemList
        totalAmountLiveData.value = ShoppingCart.total
        updateScreenState()
    }
}