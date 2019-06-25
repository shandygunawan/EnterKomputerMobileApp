package com.shandy.enterkomputermobileapp.presentation.products

import com.shandy.enterkomputermobileapp.models.Product

interface ListProductsView {
    fun showLoading(isLoading: Boolean)
    fun showError(message: String)
    fun showProducts(category: String)
}