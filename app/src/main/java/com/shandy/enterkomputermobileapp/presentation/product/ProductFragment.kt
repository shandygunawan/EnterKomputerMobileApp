package com.shandy.enterkomputermobileapp.presentation.product

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
import com.shandy.enterkomputermobileapp.utils.Constants
import com.shandy.enterkomputermobileapp.utils.image.ImageLoader
import kotlinx.android.synthetic.main.fragment_products.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ProductFragment(paramContext: Context) : Fragment(), ProductView,
    ProductFilterDialog.ProductsFilterDialogListener, ProductSortDialog.ProductsSortDialogListener {

    /*************************************************************
     *                          VARIABLES                        *
     *************************************************************/
    // System
    private var con = paramContext

    // Presenter
    private var presenter = ProductPresenter()

    // RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    // Data
    private var category = paramContext.getString(R.string.product_accessories)
    private var shownProducts : List<Product>? = null

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
        presenter.attachView(this)
        presenter.loadProducts(getString(R.string.product_accessories))
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    /*************************************************************
     *                          GETTERS                          *
     *************************************************************/
    fun getShownProducts(): List<Product>? = shownProducts

    /*************************************************************
     *                          SETTERS                          *
     *************************************************************/
    fun setShownProducts(products: List<Product>){ shownProducts = products }

    /*************************************************************
     *                          VIEW                             *
     *************************************************************/
    override fun showLoading(isLoading: Boolean) {
        if(isLoading){
            rvListProducts.visibility = View.GONE
            if(isFabOpen) setFABChildren(!isFabOpen)
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

    override fun showProducts(products: List<Product>?, category: String) {
        shownProducts = products
        if(rvListProducts != null) rvListProducts.adapter = ProductAdapter(products)
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
                    presenter.loadProducts(category)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    category = tab?.text.toString()
                    presenter.loadProducts(category)
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
            ProductFilterDialog(this@ProductFragment,
                shownProducts).show(fragmentManager, "Filter")
        }

        fabProductsSort.setOnClickListener {
            ProductSortDialog(this@ProductFragment)
                .show(fragmentManager, "Sort")
        }
    }

    /*************************************************************
     *                          SORTS                            *
     *************************************************************/
    override fun onProductsSortDialogPositiveClick(dialog: ProductSortDialog) {
        dialog.dismiss()
        presenter.sortProducts(dialog.getSortCategory(), dialog.getSortMode(),
            shownProducts, category)
    }

    override fun onProductsSortDialogNegativeClick(dialog: ProductSortDialog) {
        dialog.dismiss()
    }

    /*************************************************************
     *                          FILTERS                          *
     *************************************************************/
    override fun onProductsFilterDialogPositiveClick(dialog: ProductFilterDialog) {
        dialog.dismiss()
        presenter.filterProducts(dialog.getFilters(), shownProducts, category)
    }

    override fun onProductsFilterDialogNegativeClick(dialog: ProductFilterDialog) {
        dialog.dismiss()
    }

}