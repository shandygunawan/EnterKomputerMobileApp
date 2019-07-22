package com.shandy.enterkomputermobileapp.presentation.product

import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.presentation.BasePresenter
import com.shandy.enterkomputermobileapp.repository.ProductRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ProductPresenter: BasePresenter<ProductView>() {

    private val repo = ProductRepository()

    fun loadProducts(category: String) {
        mView?.showLoading(true)
        doAsync {
            val products = repo.loadProducts(category)

            if(products != null){
                for(product in products){
                    product.name = product.name.trim()
                }
            }

            uiThread {
                if(products != null) mView?.showProducts(products, category)
                else mView?.showError("Products failed to load. Please check your internet connection.")
                mView?.showLoading(false)
            }
        }
    }

    fun sortProducts(sortCategory: String, sortMode: String, products: List<Product>?, productCategory: String){
        mView?.showLoading(true)
        doAsync {
            val sortedProducts = repo.sortProducts(sortCategory, sortMode, products)
            uiThread {
                if(sortedProducts == null) mView?.showError("Sorting failed. Please try again.")
                else mView?.showProducts(sortedProducts, productCategory)
                mView?.showLoading(false)
            }
        }
    }

    fun filterProducts(filters: HashMap<String, String>, products: List<Product>?, category: String){
        mView?.showLoading(true)
        doAsync {
            val filteredProducts = repo.filterProducts(filters, products)
            uiThread {
                if(filteredProducts == null) mView?.showError("Filter error. Please Try again")
                else mView?.showProducts(filteredProducts, category)
                mView?.showLoading(false)
            }
        }
    }

}