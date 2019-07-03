package com.shandy.enterkomputermobileapp.presentation

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.presentation.home.HomeFragment
import com.shandy.enterkomputermobileapp.presentation.howto.HowToFragment
import com.shandy.enterkomputermobileapp.presentation.products.ProductsFragment
import com.shandy.enterkomputermobileapp.utils.Constants

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    /*************************************************************
     *                          VARIABLES                        *
     *************************************************************/
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

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

        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)

        setFragment(Constants.Navigation.NAVIGATION_PRODUCTS)


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
        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView = findViewById(R.id.nvMain)
        navView.setNavigationItemSelectedListener(this)
        navView.setCheckedItem(R.id.navProducts)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        Handler().postDelayed({
            run {
                when (item.itemId) {
                    R.id.navHome -> setFragment(Constants.Navigation.NAVIGATION_HOME)
                    R.id.navProducts -> setFragment(Constants.Navigation.NAVIGATION_PRODUCTS)
                    R.id.navSimulation -> {}
                    R.id.navHowTo -> setFragment(Constants.Navigation.NAVIGATION_HOW_TO)
                    R.id.navTips -> {}
                    R.id.navOrderTracking -> { }
                }
            }
        }, 200)

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /*************************************************************
     *                          FRAGMENTS                        *
     *************************************************************/
    private fun setFragment(mode: String){
        when(mode) {
            Constants.Navigation.NAVIGATION_HOME -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.layoutMain, HomeFragment())
                    .commit()
            }
            Constants.Navigation.NAVIGATION_PRODUCTS -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.layoutMain, ProductsFragment())
                    .commit()
            }
            Constants.Navigation.NAVIGATION_SIMULATION -> {}
            Constants.Navigation.NAVIGATION_HOW_TO -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.layoutMain, HowToFragment())
                    .commit()
            }
            Constants.Navigation.NAVIGATION_TIPS -> {}
            Constants.Navigation.NAVIGATION_ORDER_TRACKING -> {}
            else ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.layoutMain, ProductsFragment())
                    .commit()
            }
        }
    }
}
