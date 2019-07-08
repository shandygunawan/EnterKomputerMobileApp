package com.shandy.enterkomputermobileapp.presentation.products

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.network.Endpoints.ProductEndpoints
import com.shandy.enterkomputermobileapp.network.RetrofitClient
import com.shandy.enterkomputermobileapp.utils.Constants
import com.shandy.enterkomputermobileapp.utils.image.ImageLoader
import kotlinx.android.synthetic.main.fragment_products.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.uiThread

class ProductsFragment(paramContext: Context) : Fragment(), ProductsView, ProductsFilterDialog.ProductsFilterDialogListener {

    /*************************************************************
     *                          VARIABLES                        *
     *************************************************************/
    // System
    private var con = paramContext

    // RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    // Data
    private var products: List<Product>? = null
    private var subCategories = ArrayList<String>()
    // FAB
    private var isFabOpen : Boolean = false
    private val fab_open = AnimationUtils.loadAnimation(con, R.anim.fab_open)
    private val fab_close = AnimationUtils.loadAnimation(con, R.anim.fab_close)

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

        /* Init fab */
        initFAB()

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
            if(isFabOpen == true) setFABChildren(!isFabOpen)
            fabProductsSortFilter.hide()
            pbListProducts.visibility = View.VISIBLE
        }
        else {
            rvListProducts.visibility = View.VISIBLE
            fabProductsSortFilter.show()
            pbListProducts.visibility = View.GONE
        }
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProducts(category: String) {
        showLoading(isLoading = true)
        doAsync {
            val webServices = RetrofitClient
                .getInstance(Constants.URLS.URL_PRODUCT_BASE)
                .create(ProductEndpoints::class.java)

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

            if(products != null){
                val isSubCatInserted = HashMap<String, Boolean>()
                subCategories.add(getString(R.string.all_subcategory))
                for(product in products!!){
                    if(product.subcategory_description != Constants.Strings.STRING_EMPTY &&
                        (isSubCatInserted[product.subcategory_description] != true
                                || isSubCatInserted[product.subcategory_description] == null)){
                        subCategories.add(product.subcategory_description)
                        isSubCatInserted[product.subcategory_description] = true
                    }
                }
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

    /*************************************************************
     *                          FAB                              *
     *************************************************************/
    private fun setFABChildren(isOn: Boolean){
        if(isOn){
            tvProductsFilter.visibility = View.VISIBLE
            tvProductsSort.visibility = View.VISIBLE
            fabProductsSort.startAnimation(fab_open)
            fabProductsFilter.startAnimation(fab_open)
            fabProductsSort.isClickable = true
            fabProductsFilter.isClickable = true
            isFabOpen = true
        }
        else {
            tvProductsFilter.visibility = View.INVISIBLE
            tvProductsSort.visibility = View.INVISIBLE
            fabProductsSort.startAnimation(fab_close)
            fabProductsFilter.startAnimation(fab_close)
            fabProductsSort.isClickable = false
            fabProductsFilter.isClickable = false
            isFabOpen = false
        }
    }

    private fun initFAB(){
        fabProductsSortFilter.onClick {
            setFABChildren(!isFabOpen)
        }

        fabProductsFilter.setOnClickListener {
            ProductsFilterDialog(this@ProductsFragment,
                products, subCategories).show(fragmentManager, "Filter")
        }
    }

    /*************************************************************
     *                          FILTERS                          *
     *************************************************************/
    override fun onProductsFilterDialogPositiveClick(dialog: ProductsFilterDialog) {
        dialog.dismiss()
        filterProducts(dialog.getFilters())
    }

    override fun onProductsFilterDialogNegativeClick(dialog: ProductsFilterDialog) {
        dialog.dismiss()
    }

    private fun filterName(name: String?, toFilter: List<Product>?): List<Product>?{
        if(name == null) return toFilter
        return toFilter?.filter {
            it.name.contains(name.toString())
        } ?: products?.filter {
            it.name.contains(name.toString())
        }
    }

    private fun filterBrand(brand: String?, toFilter: List<Product>?): List<Product>?{
        if(brand == null) return toFilter
        return toFilter?.filter {
            it.brand_description.contains(brand.toString())
        } ?: products?.filter {
            it.brand_description.contains(brand.toString())
        }
    }

    private fun filterSubCategory(subcat: String?, toFilter: List<Product>?): List<Product>?{
        if(subcat == getString(R.string.all_subcategory)) return toFilter
        return toFilter?.filter {
            it.subcategory_description.contains(subcat.toString())
        } ?: products?.filter {
            it.subcategory_description.contains(subcat.toString())
        }
    }

    private fun filterPrice(price: String?, toFilter: List<Product>?): List<Product>? {
        if(price == null) return toFilter

        val prices = price.split(Constants.Separators.SEPARATOR_COMMA)
        val minPrice = prices.first().toInt()
        val maxPrice = prices.last().toInt()

        return toFilter?.filter {
            it.price.toInt() in minPrice..maxPrice
        } ?: products?.filter {
            it.price.toInt() in minPrice..maxPrice
        }
    }

    private fun filterLink(state: String?, link: String, toFilter: List<Product>?): List<Product>? {
        if(state == Constants.States.CHECKBOX_UNCHECKED) return toFilter
        return when (link) {
            Constants.ECommerces.ECOMMERCE_TOKOPEDIA -> toFilter?.filter {
                !it.link_toped.isNullOrBlank()
            } ?: products?.filter {
                !it.link_toped.isNullOrBlank()
            }
            Constants.ECommerces.ECOMMERCE_BUKALAPAK -> toFilter?.filter {
                !it.link_bukalapak.isNullOrBlank()
            } ?: products?.filter {
                !it.link_bukalapak.isNullOrBlank()
            }
            Constants.ECommerces.ECOMMERCE_SHOPEE -> toFilter?.filter {
                !it.link_shopee.isNullOrBlank()
            } ?: products?.filter {
                !it.link_shopee.isNullOrBlank()
            }
            else -> toFilter
        }
    }

    private fun filterProducts(filters: HashMap<String, String>){
        showLoading(true)
        var filteredProducts : List<Product>? = null
        doAsync {
            filteredProducts = filterName(filters[Constants.Filters.FILTER_PRODUCTS_NAME],
                filteredProducts)
            filteredProducts = filterBrand(filters[Constants.Filters.FILTER_PRODUCTS_BRAND],
                filteredProducts)
            filteredProducts = filterSubCategory(filters[Constants.Filters.FILTER_PRODUCTS_SUBCATEGORY],
                filteredProducts)
            filteredProducts = filterPrice(filters[Constants.Filters.FILTER_PRODUCTS_PRICE],
                filteredProducts)
            filteredProducts = filterLink(filters[Constants.Filters.FILTER_PRODUCTS_LINK_TOKOPEDIA],
                Constants.ECommerces.ECOMMERCE_TOKOPEDIA, filteredProducts)
            filteredProducts = filterLink(filters[Constants.Filters.FILTER_PRODUCTS_LINK_BUKALAPAK],
                Constants.ECommerces.ECOMMERCE_BUKALAPAK, filteredProducts)
            filteredProducts = filterLink(filters[Constants.Filters.FILTER_PRODUCTS_LINK_SHOPEE],
                Constants.ECommerces.ECOMMERCE_SHOPEE, filteredProducts)


            uiThread {
                if(filteredProducts != null && rvListProducts != null){
                    rvListProducts.adapter = ProductAdapter(filteredProducts)
                    showLoading(false)
                }
            }
        }
    }

}