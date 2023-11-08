package com.example.myapplication.shoppingcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var cartItemsRecyclerView: RecyclerView
    private lateinit var toalAmountTv: TextView
    private lateinit var emptyCartTv: TextView
    private lateinit var cartRelativeLayout: RelativeLayout
    private lateinit var adapter: ShoppingKartAdapter
    private lateinit var viewmodel: ShoppingCartViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)
        cartItemsRecyclerView = findViewById(R.id.sc_recycler_view)
        toalAmountTv = findViewById(R.id.shopping_total_amount)
        emptyCartTv = findViewById(R.id.shopping_cart_empty)
        cartRelativeLayout = findViewById(R.id.sc_relative_layout)
        viewmodel = ViewModelProvider(this).get(ShoppingCartViewModel::class.java)
        adapter = ShoppingKartAdapter {
            viewmodel.onItemCountChange(it)
        }
        cartItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        cartItemsRecyclerView.adapter = adapter

        viewmodel.viewStateLiveData.observe(this) {
            it?.let {
                when (it) {
                    ShoppingCartState.EMPTY_CART -> {
                        cartRelativeLayout.visibility = View.INVISIBLE
                        emptyCartTv.visibility = View.VISIBLE
                    }

                    ShoppingCartState.FILLED_CART -> {
                        cartRelativeLayout.visibility = View.VISIBLE
                        emptyCartTv.visibility = View.INVISIBLE
                    }
                }
            }
        }

        viewmodel.cartItemListLiveData.observe(this) {
            Log.d("sc_bug", "list value observed  $it")
            it?.let {
                adapter.updateListAndRefresh(it)
            }
        }
        viewmodel.totalAmountLiveData.observe(this) {
            it?.let {
                toalAmountTv.text = it
            }
        }
    }
}