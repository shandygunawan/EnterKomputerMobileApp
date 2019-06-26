package com.shandy.enterkomputermobileapp.repository


interface ProductData{

    interface Repository {
        fun loadProduct(productId: Long)
        fun loadProducts(category: String)
        fun loadProducts(category: String, filters: HashMap<String, String>)
    }

}