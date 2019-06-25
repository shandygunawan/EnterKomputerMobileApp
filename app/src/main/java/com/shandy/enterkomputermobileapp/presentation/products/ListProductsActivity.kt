package com.shandy.enterkomputermobileapp.presentation.products

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
import com.shandy.enterkomputermobileapp.network.ProductEndpoints
import com.shandy.enterkomputermobileapp.network.RetrofitClient
import com.shandy.enterkomputermobileapp.utils.Constants
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListProductsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
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
                .getInstance(Constants.URL_PRODUCT_BASE)
                .create(ProductEndpoints::class.java)

            when(category){
                "accessories" -> products = webServices.getListAccessories().execute().body()
                "aio" -> products = webServices.getListAIO().execute().body()
                "casing" -> products = webServices.getListCasing().execute().body()
                "coolerfan" -> products = webServices.getListCoolerFan().execute().body()
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
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

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
                        0 -> showProducts("accessories")
                        1 -> showProducts("aio")
                        2 -> showProducts("coolerfan")
                    }
                }
            }
        )
    }
}
