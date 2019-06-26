package com.shandy.enterkomputermobileapp.presentation

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.network.Endpoints
import com.shandy.enterkomputermobileapp.network.RetrofitClient
import com.shandy.enterkomputermobileapp.presentation.products.ListProductAdapter
import com.shandy.enterkomputermobileapp.presentation.products.ListProductsView
import com.shandy.enterkomputermobileapp.utils.Constants
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    ListProductsView {

    /* Member Variables */
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListProductAdapter
    private var products: List<Product>? = null

    /*************************************************************
     *                          LIFECYCLE                        *
     *************************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /* Initialize Navigation Drawer */
        initNavigationDrawer()

        /* Initialize Tab Layout */
        initTabLayout()

        /* Initialize RecyclerView */
        linearLayoutManager = LinearLayoutManager(this)
        rvListProducts.layoutManager = linearLayoutManager
        showProducts("accessories")
    }

    /*************************************************************
     *                          VIEW                             *
     *************************************************************/

    override fun showLoading(isLoading: Boolean) {
        if(isLoading){
            rvListProducts.visibility = View.GONE
            pbListProducts.visibility = View.VISIBLE
        }
        else {
            rvListProducts.visibility = View.VISIBLE
            pbListProducts.visibility = View.GONE
        }
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProducts(category: String) {
        showLoading(isLoading = true)
        doAsync {
            val webServices = RetrofitClient()
                .getInstance(Constants.URLS.URL_PRODUCT_BASE)
                .create(Endpoints.ProductEndpoints::class.java)

            when(category){
                Constants.Products.PRODUCT_ACCESSORIES -> products = webServices.getListAccessories().execute().body()
                Constants.Products.PRODUCT_AIO -> products = webServices.getListAIO().execute().body()
                Constants.Products.PRODUCT_CASING -> products = webServices.getListCasing().execute().body()
                Constants.Products.PRODUCT_COOLER -> products = webServices.getListCoolerFan().execute().body()
                Constants.Products.PRODUCT_DRAWING -> products = webServices.getListDrawingTablet().execute().body()
                Constants.Products.PRODUCT_DRONE -> products = webServices.getListDrone().execute().body()
                Constants.Products.PRODUCT_FLASHDISK -> products = webServices.getListFlashdisk().execute().body()
                else -> products = null
            }

            uiThread {
                adapter =
                    ListProductAdapter(
                        products
                    )
                rvListProducts.adapter = adapter
                showLoading(isLoading = false)
            }
        }
    }

    /*************************************************************
     *                      OPTIONS MENU                         *
     *************************************************************/

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*************************************************************
     *                  NAVIGATION DRAWER                        *
     *************************************************************/

    private fun initNavigationDrawer(){
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navView: NavigationView = findViewById(R.id.nvMain)
        navView.setNavigationItemSelectedListener(this)
        navView.setCheckedItem(R.id.navProducts)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.navProducts -> {

            }
            R.id.navSimulation -> {

            }
            R.id.navHowTo -> {

            }
            R.id.navTips -> {

            }
            R.id.navOrderTracking -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /*************************************************************
     *                        TAB LAYOUT                         *
     *************************************************************/

    private fun initTabLayout(){

        // Set colors for icon states
        val colors: ColorStateList = if(Build.VERSION.SDK_INT >= 23){
            resources.getColorStateList(R.color.tab_icon_products, theme)
        } else {
            resources.getColorStateList(R.color.tab_icon_products)
        }

        for(i in 0..tabLayoutListProducts.tabCount){
            val tab = tabLayoutListProducts.getTabAt(i)
            var icon = tab?.icon

            if(icon != null){
                icon = DrawableCompat.wrap(icon)
                DrawableCompat.setTintList(icon, colors)
            }
        }

        tabLayoutListProducts.addOnTabSelectedListener(
            object: TabLayout.OnTabSelectedListener{
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        0 -> showProducts(Constants.Products.PRODUCT_ACCESSORIES)
                        1 -> showProducts(Constants.Products.PRODUCT_AIO)
                        2 -> showProducts(Constants.Products.PRODUCT_CASING)
                        3 -> showProducts(Constants.Products.PRODUCT_COOLER)
                        4 -> showProducts(Constants.Products.PRODUCT_DRAWING)
                        5 -> showProducts(Constants.Products.PRODUCT_DRONE)
                        6 -> showProducts(Constants.Products.PRODUCT_FLASHDISK)
                    }
                }
            }
        )
    }
}
