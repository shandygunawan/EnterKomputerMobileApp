package com.shandy.enterkomputermobileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.shandy.enterkomputermobileapp.adapters.ListProductAdapter
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.network.ProductEndpoints
import com.shandy.enterkomputermobileapp.network.RetrofitClient
import com.shandy.enterkomputermobileapp.utils.Constants
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    /* Recycler View */
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListProductAdapter
    private var products: List<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        /* Initialize Recycler view */
        linearLayoutManager = LinearLayoutManager(this)
        rvListProduct.layoutManager = linearLayoutManager

        setRecyclerView(category = "accessories", isRvCreated = false)
        initTabNavigation()
    }

    /*******************************************************
                            Navigation View
     ******************************************************/


    /*******************************************************
                          TAB NAVIGATION
     ******************************************************/
    private fun initTabNavigation() {
        /*
        navBottomProducts.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ivCategoryAccessories -> {
                    longToast("Accessories selected")
                    setRecyclerView(category = "accessories", isRvCreated = true)
                    true
                }
                R.id.ivCategoryAIO -> {
                    longToast("AIO Selected")
                    setRecyclerView(category = "aio", isRvCreated = true)
                    true
                }
                else -> false
            }
        }
        */
    }

    /*******************************************************
                        RecycleView List
     ******************************************************/
    private fun setRecyclerView(category: String, isRvCreated: Boolean) {
        doAsync {
            val webServices = RetrofitClient()
                .getInstance(Constants.URL_PRODUCT_BASE)
                .create(ProductEndpoints::class.java)

            when(category){
                "accessories" -> products = webServices.getListAccessories().execute().body()
                "aio" -> products = webServices.getListAIO().execute().body()
                else -> products = null
            }

            /*
            uiThread{
                adapter = ListProductAdapter(products)
                rvListProduct.adapter = adapter
            }
            */
        }
    }
}
