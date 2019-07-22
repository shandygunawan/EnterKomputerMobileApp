package com.shandy.enterkomputermobileapp.presentation.product

import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.presentation.BaseView

interface ProductView : BaseView {
    fun showProducts(products: List<Product>?, category: String)
}