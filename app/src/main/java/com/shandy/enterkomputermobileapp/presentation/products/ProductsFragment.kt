package com.shandy.enterkomputermobileapp.presentation.products

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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

class ProductsFragment(paramContext: Context) : Fragment(), ProductsView,
    ProductsFilterDialog.ProductsFilterDialogListener, ProductsSortDialog.ProductsSortDialogListener {

    /*************************************************************
     *                          VARIABLES                        *
     *************************************************************/
    // System
    private var con = paramContext

    // RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    // Data
    private var products: List<Product>? = null
    private var shownProducts : List<Product>? = null
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
                getString(R.string.product_accessories) -> products = webServices.getListAccessories().execute().body()
                getString(R.string.product_aio) -> products = webServices.getListAIO().execute().body()
                getString(R.string.product_casing) -> products = webServices.getListCasing().execute().body()
                getString(R.string.product_cooler) -> products = webServices.getListCoolerFan().execute().body()
                getString(R.string.product_drawing) -> products = webServices.getListDrawingTablet().execute().body()
                getString(R.string.product_drone) -> products = webServices.getListDrone().execute().body()
                getString(R.string.product_fd) -> products = webServices.getListFlashdisk().execute().body()
                else -> products = null
            }
            for(product in products!!){
                product.name = product.name.trim()
            }
            shownProducts = products
            subCategories.clear()
            if(products != null){
                val isSubCatInserted = HashMap<String, Boolean>()
                for(product in products!!){
                    if(product.subcategory_description != Constants.Strings.STRING_EMPTY &&
                        (isSubCatInserted[product.subcategory_description] != true
                                || isSubCatInserted[product.subcategory_description] == null)){
                        subCategories.add(product.subcategory_description)
                        isSubCatInserted[product.subcategory_description] = true
                    }
                }
            }
            subCategories.sort()
            subCategories.add(0, getString(R.string.all_subcategory))

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

        for(i in 0 until tabLayoutListProducts.tabCount-1){
            val tab = tabLayoutListProducts.getTabAt(i)

            /* Optimize Icons */

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
                    showProducts(tab?.text.toString())
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    showProducts(tab?.text.toString())
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

        fabProductsSort.setOnClickListener {
            ProductsSortDialog(this@ProductsFragment)
                .show(fragmentManager, "Sort")
        }
    }

    /*************************************************************
     *                          SORTS                            *
     *************************************************************/
    override fun onProductsSortDialogPositiveClick(dialog: ProductsSortDialog) {
        dialog.dismiss()
        sortProducts(category = dialog.getSortCategory(), mode = dialog.getSortMode())
    }

    override fun onProductsSortDialogNegativeClick(dialog: ProductsSortDialog) {
        dialog.dismiss()
    }

    private fun sortProducts(category: String, mode: String){
        showLoading(true)
        doAsync {
            if(shownProducts != null){
                if(mode == "Ascending"){
                    when(category){
                        "Name" -> shownProducts = shownProducts!!.sortedBy { it.name }
                        "Brand" -> shownProducts = shownProducts!!.sortedBy { it.brand_description }
                        "SubCategory" -> shownProducts = shownProducts!!.sortedBy { it.subcategory_description }
                        "Price" -> shownProducts = shownProducts!!.sortedBy { it.price }
                    }
                }
                else if (mode == "Descending") {
                    when(category){
                        "Name" -> shownProducts = shownProducts!!.sortedByDescending { it.name }
                        "Brand" -> shownProducts = shownProducts!!.sortedByDescending { it.brand_description }
                        "SubCategory" -> shownProducts = shownProducts!!.sortedByDescending { it.subcategory_description }
                        "Price" -> shownProducts = shownProducts!!.sortedByDescending { it.price }
                    }
                }
            }
            else {
                if(mode == "Ascending"){
                    when(category){
                        "Name" -> shownProducts = products!!.sortedBy { it.name }
                        "Brand" -> shownProducts = products!!.sortedBy { it.brand_description }
                        "SubCategory" -> shownProducts = products!!.sortedBy { it.subcategory_description }
                        "Price" -> shownProducts = products!!.sortedBy { it.price }
                    }
                }
                else if (mode == "Descending") {
                    when(category){
                        "Name" -> shownProducts = products!!.sortedByDescending { it.name }
                        "Brand" -> shownProducts = products!!.sortedByDescending { it.brand_description }
                        "SubCategory" -> shownProducts = products!!.sortedByDescending { it.subcategory_description }
                        "Price" -> shownProducts = products!!.sortedByDescending { it.price }
                    }
                }
            }

            uiThread {
                if(shownProducts != null && rvListProducts != null){
                    rvListProducts.adapter = ProductAdapter(shownProducts)
                    showLoading(false)
                }
            }
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
            it.price in minPrice..maxPrice
        } ?: products?.filter {
            it.price in minPrice..maxPrice
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
        doAsync {
            shownProducts = filterName(filters[Constants.Filters.FILTER_PRODUCTS_NAME],
                shownProducts)
            shownProducts = filterBrand(filters[Constants.Filters.FILTER_PRODUCTS_BRAND],
                shownProducts)
            shownProducts = filterSubCategory(filters[Constants.Filters.FILTER_PRODUCTS_SUBCATEGORY],
                shownProducts)
            shownProducts = filterPrice(filters[Constants.Filters.FILTER_PRODUCTS_PRICE],
                shownProducts)
            shownProducts = filterLink(filters[Constants.Filters.FILTER_PRODUCTS_LINK_TOKOPEDIA],
                Constants.ECommerces.ECOMMERCE_TOKOPEDIA, shownProducts)
            shownProducts = filterLink(filters[Constants.Filters.FILTER_PRODUCTS_LINK_BUKALAPAK],
                Constants.ECommerces.ECOMMERCE_BUKALAPAK, shownProducts)
            shownProducts = filterLink(filters[Constants.Filters.FILTER_PRODUCTS_LINK_SHOPEE],
                Constants.ECommerces.ECOMMERCE_SHOPEE, shownProducts)

            uiThread {
                if(shownProducts != null && rvListProducts != null){
                    rvListProducts.adapter = ProductAdapter(shownProducts)
                    showLoading(false)
                }
            }
        }
    }

}