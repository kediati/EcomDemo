package com.example.myapplication.shoppingcart

import com.example.myapplication.model.Product
import java.math.BigDecimal
import java.math.RoundingMode

class ShoppingCart {
    companion object {
        val cartItemList = mutableListOf<CartItem>()
        val total: String
            get() {
                var cartTotal = BigDecimal("0.0")
                cartItemList.forEach {
                    cartTotal = cartTotal.add(it.totalItemPrice)
                }
                return cartTotal.setScale(2, RoundingMode.HALF_UP).toString()
            }

        fun addNewItem(product: Product) {
            val item = cartItemList.find { product.id == it.product.id }
            if (item == null) {
                cartItemList.add(CartItem(product, 1))
            }
        }

        fun removeItem(productId: Int): Boolean {
            val item = cartItemList.find { productId == it.product.id }
            item?.let {
                cartItemList.remove(item)
                return true
            }
            return false
        }

        fun incrementItemQuantity(productId: Int) {
            val item = cartItemList.find { productId == it.product.id }
            item?.let {
                it.quantity++
            }
        }

        fun decrementItemQuantity(productId: Int) {
            val item = cartItemList.find { productId == it.product.id }
            item?.let {
                it.quantity--
                if (it.quantity <= 0) {
                  cartItemList.remove(it)
                }
            }
        }

        fun getSize(): Int = cartItemList.size

        fun getItemAtIndex(index: Int): CartItem {
            return cartItemList[index]
        }
    }
}

class CartItem(val product: Product, var quantity: Int = 1) {
    val totalItemPrice: BigDecimal
        get() {
            return (product.price.toBigDecimalOrNull() ?: BigDecimal("0.0"))
                .multiply(quantity.toBigDecimal())
                .setScale(2, RoundingMode.HALF_UP)
        }
}
