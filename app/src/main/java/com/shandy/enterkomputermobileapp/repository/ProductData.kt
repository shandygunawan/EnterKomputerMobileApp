package com.shandy.enterkomputermobileapp.repository

import com.shandy.enterkomputermobileapp.models.Product


interface ProductData{

    interface Repository {
        fun loadProducts(category: String) : List<Product>?
        fun sortProducts(sortCategory: String, sortMode: String, products: List<Product>?) : List<Product>?
        fun filterProducts(filters: HashMap<String, String>, products: List<Product>?): List<Product>?
    }

    interface LocalDataSource {
        fun sortProducts(sortCategory: String, sortMode: String, products: List<Product>?) : List<Product>?
        fun filterProducts(filters: HashMap<String, String>, products: List<Product>?): List<Product>?
    }

    interface RemoteDataSource {
        fun loadProducts(category: String): List<Product>?
    }

}