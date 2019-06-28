package com.shandy.enterkomputermobileapp.presentation.products

import com.shandy.enterkomputermobileapp.presentation.BaseView

interface ProductsView : BaseView {
    fun showProducts(category: String)
}