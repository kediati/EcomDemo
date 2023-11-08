package com.example.myapplication.shoppingcart

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ShoppingKartAdapter(private val onCartQuantityEvent: (cartQuantityEvent: CartQuantityEvent) -> Unit) :
    RecyclerView.Adapter<CartItemViewHolder>() {

    private var cartItemList = emptyList<CartItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shopping_cart, parent, false)
        return CartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.onBind(cartItemList[position], onCartQuantityEvent)
    }

    override fun getItemCount(): Int {
        Log.d("sc_bug", "Queried list count is ${ShoppingCart.getSize()}")
        return cartItemList.size
    }

    fun updateListAndRefresh(updatedList: List<CartItem>) {
        cartItemList = updatedList
        notifyDataSetChanged()
    }
}

class CartItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val quantityTv = view.findViewById<TextView>(R.id.si_quantity_label)
    private val nameTv = view.findViewById<TextView>(R.id.si_product_name)
    private val totalTv = view.findViewById<TextView>(R.id.shopping_item_total_amount)
    private val subtactIb = view.findViewById<ImageButton>(R.id.si_subtract_quantity)
    private val addIb = view.findViewById<ImageButton>(R.id.si_add_quantity)

    fun onBind(
        cartItem: CartItem,
        onCartQuantityEvent: (cartQuantityEvent: CartQuantityEvent) -> Unit
    ) {
        nameTv.text = cartItem.product.title
        quantityTv.text = cartItem.quantity.toString()
        totalTv.text = cartItem.totalItemPrice.toString()
        addIb.setOnClickListener {
            onCartQuantityEvent(
                CartQuantityEvent(
                    CartQuantityAction.INCREMENT,
                    cartItem.product.id
                )
            )
        }
        subtactIb.setOnClickListener {
            onCartQuantityEvent(
                CartQuantityEvent(
                    CartQuantityAction.DECREMENT,
                    cartItem.product.id
                )
            )
        }
    }
}
