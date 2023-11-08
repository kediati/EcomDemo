package com.example.myapplication.ProductList

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Product
import com.example.myapplication.shoppingcart.ShoppingCart
import com.squareup.picasso.Picasso
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject


class ProductListAdapter @Inject constructor() : RecyclerView.Adapter<ProductViewHolder>() {

    private var productList: List<Product> = emptyList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(updatedProductList: List<Product>) {
        productList = updatedProductList
    }
}


class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val productName: TextView = itemView.findViewById(R.id.product_name)
    private val productDesc: TextView = itemView.findViewById(R.id.product_description)
    private val productPrice: TextView = itemView.findViewById(R.id.product_price)
    private val productImage: AppCompatImageView = itemView.findViewById(R.id.product_image)
    private val addButton: AppCompatButton = itemView.findViewById(R.id.add_to_cart_button)


    fun bind(product: Product) {
        productName.text = product.title
        productDesc.text = product.description
        Picasso.get()
            .load(product.image)
            .fit()
            .into(productImage)
        productPrice.text = product.price
        addButton.setOnClickListener {
            ShoppingCart.addNewItem(product)

        }
    }
}