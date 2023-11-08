package com.example.myapplication.ProductList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Product
import com.example.myapplication.shoppingcart.ShoppingCartActivity
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {

    private lateinit var productRecyclerView: RecyclerView
    @Inject
    lateinit var productAdapter: ProductListAdapter
    private lateinit var progressLoader: ProgressBar
    private lateinit var actionBar: MaterialToolbar
    private lateinit var productListViewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poduct_list)
        actionBar = findViewById(R.id.action_bar)
        productRecyclerView = findViewById(R.id.product_recycler_view)
        progressLoader = findViewById(R.id.loader)
        setSupportActionBar(actionBar)

        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productRecyclerView.adapter = productAdapter

        productListViewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)

        productListViewModel.uiState.observe(this) {
            it?.let { changeViewState(it) }
        }

        productListViewModel.productListLiveData.observe(this) {
            it?.let {
                productAdapter.updateProductList(it)
                productAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun changeViewState(productListState: ProductListState?) {
        when (productListState) {
            ProductListState.LOADING -> {
                progressLoader.visibility = View.VISIBLE
                productRecyclerView.visibility = View.GONE
            }

            else -> {
                progressLoader.visibility = View.GONE
                productRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_list_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_shopping_cart -> {
                startActivity(Intent(this, ShoppingCartActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

