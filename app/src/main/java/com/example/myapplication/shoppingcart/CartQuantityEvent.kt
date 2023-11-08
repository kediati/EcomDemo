package com.example.myapplication.shoppingcart

data class CartQuantityEvent(val cartQuantityAction: CartQuantityAction, val productId: Int)


enum class CartQuantityAction {
    INCREMENT, DECREMENT
}
