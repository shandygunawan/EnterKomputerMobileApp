package com.shandy.enterkomputermobileapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.shandy.enterkomputermobileapp.adapters.ListProductAdapter
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.network.RetrofitClient
import com.shandy.enterkomputermobileapp.network.ProductEndpoints
import com.shandy.enterkomputermobileapp.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    /* Recycler View */
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListProductAdapter
    private var products: List<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        /* Initialize Recycler view */
        linearLayoutManager = LinearLayoutManager(this)
        rvListProduct.layoutManager = linearLayoutManager

        setRecyclerView(category = "accessories", isRvCreated = false)
        initBottomNavigation()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /*******************************************************
                            Navigation View
     ******************************************************/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /*******************************************************
                        Bottom Navigation
     ******************************************************/
    private fun initBottomNavigation() {
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

            uiThread{
                adapter = ListProductAdapter(products)
                rvListProduct.adapter = adapter
            }
        }
    }
}
