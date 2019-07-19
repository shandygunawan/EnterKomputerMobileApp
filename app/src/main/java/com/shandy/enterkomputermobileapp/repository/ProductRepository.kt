package com.shandy.enterkomputermobileapp.repository

import com.shandy.enterkomputermobileapp.models.Product

class ProductRepository: ProductData.Repository {

    private val local = ProductLocalDataSource()
    private val remote = ProductRemoteDataSource()

    override fun loadProducts(category: String) : List<Product>? = remote.loadProducts(category)

    override fun sortProducts(
        sortCategory: String,
        sortMode: String,
        products: List<Product>?
    ): List<Product>? = local.sortProducts(sortCategory, sortMode, products)

    override fun filterProducts(
        filters: HashMap<String, String>,
        products: List<Product>?
    ): List<Product>? = local.filterProducts(filters, products)
}