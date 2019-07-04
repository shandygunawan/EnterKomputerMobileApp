package com.shandy.enterkomputermobileapp.presentation.products

import android.content.res.ColorStateList
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.models.AnimationItem
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.network.Endpoints.ProductEndpoints
import com.shandy.enterkomputermobileapp.network.RetrofitClient
import com.shandy.enterkomputermobileapp.utils.Constants
import com.shandy.enterkomputermobileapp.utils.image.ImageLoader
import kotlinx.android.synthetic.main.fragment_products.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ProductsFragment : Fragment(), ProductsView {

    /*************************************************************
     *                          VARIABLES                        *
     *************************************************************/
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ProductAdapter
    private var products: List<Product>? = null

    /*************************************************************
     *                          LIFECYCLE                        *
     *************************************************************/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Initialize Tab Layout */
        initTabLayout()

        /* Initialize RecyclerView */
        initRecyclerView()
        showProducts(Constants.Products.PRODUCT_ACCESSORIES)
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
                .create(ProductEndpoints::class.java)
2
            when(category){
                Constants.Products.PRODUCT_ACCESSORIES -> products = webServices.getListAccessories().execute().body()?.sortedBy { it.name }
                Constants.Products.PRODUCT_AIO -> products = webServices.getListAIO().execute().body()?.sortedBy { it.name }
                Constants.Products.PRODUCT_CASING -> products = webServices.getListCasing().execute().body()?.sortedBy { it.name }
                Constants.Products.PRODUCT_COOLER -> products = webServices.getListCoolerFan().execute().body()?.sortedBy { it.name }
                Constants.Products.PRODUCT_DRAWING -> products = webServices.getListDrawingTablet().execute().body()?.sortedBy { it.name }
                Constants.Products.PRODUCT_DRONE -> products = webServices.getListDrone().execute().body()?.sortedBy { it.name }
                Constants.Products.PRODUCT_FLASHDISK -> products = webServices.getListFlashdisk().execute().body()?.sortedBy { it.name }
                else -> products = null
            }

            uiThread {
                if(rvListProducts != null){
                    rvListProducts.adapter = ProductAdapter(products)
                    showLoading(isLoading = false)
                }
            }
        }
    }

    /*************************************************************
     *                        TAB LAYOUT                         *
     *************************************************************/

    private fun initTabLayout(){

        // Set colors for icon states
        val colors: ColorStateList = if(Build.VERSION.SDK_INT >= 23){
            resources.getColorStateList(R.color.tab_icon_products, context?.theme)
        } else {
            resources.getColorStateList(R.color.tab_icon_products)
        }

        for(i in 0..tabLayoutListProducts.tabCount){
            val tab = tabLayoutListProducts.getTabAt(i)

            /* Optmize Icons */
            tab?.icon = BitmapDrawable(resources, ImageLoader.decodeSampledBitmapFromResource(
                resources, Constants.Lists.LIST_PRODUCTS[i], R.dimen._30sdp, R.dimen._30sdp)
            )

            /* Change Icon color tint to colorPrimary when selected */
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

    /*************************************************************
     *                        Recycler View                      *
     *************************************************************/
    private fun initRecyclerView(){
        linearLayoutManager = LinearLayoutManager(context)
        rvListProducts.layoutManager = linearLayoutManager
    }
}