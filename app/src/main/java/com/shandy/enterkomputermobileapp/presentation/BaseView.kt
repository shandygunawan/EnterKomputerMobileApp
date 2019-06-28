package com.shandy.enterkomputermobileapp.presentation

interface BaseView {
    fun showLoading(isLoading: Boolean)
    fun showError(message: String)
}