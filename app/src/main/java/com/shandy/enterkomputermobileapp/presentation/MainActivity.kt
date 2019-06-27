package com.shandy.enterkomputermobileapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.presentation.products.ProductsFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    /*************************************************************
     *                          VARIABLES                        *
     *************************************************************/
    private lateinit var toolbar: Toolbar
    private lateinit var productsFragment: ProductsFragment

    /*************************************************************
     *                          LIFECYCLE                        *
     *************************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbar)

        /* Initialize Navigation Drawer */
        initNavigationDrawer()

        initFragment("products")
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
            R.id.navProducts -> initFragment("products")
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
     *                          FRAGMENTS                        *
     *************************************************************/
    private fun initFragment(mode: String){
        when(mode) {
            "products" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.layoutMain, ProductsFragment())
                    .commit()
            }
            else ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.layoutMain, ProductsFragment())
                    .commit()
            }
        }
    }
}
