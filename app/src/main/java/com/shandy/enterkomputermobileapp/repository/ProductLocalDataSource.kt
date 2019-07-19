package com.shandy.enterkomputermobileapp.repository

import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.presentation.MainActivity
import com.shandy.enterkomputermobileapp.utils.Constants

class ProductLocalDataSource : ProductData.LocalDataSource {

    override fun sortProducts(
        sortCategory: String,
        sortMode: String,
        products: List<Product>?
    ): List<Product>? {
        var sortedProducts = products
        if(products != null){
            if(sortMode == "Ascending"){
                when(sortCategory){
                    "Name" -> sortedProducts = products.sortedBy { it.name }
                    "Brand" -> sortedProducts = products.sortedBy { it.brand_description }
                    "SubCategory" -> sortedProducts = products.sortedBy { it.subcategory_description }
                    "Price" -> sortedProducts = products.sortedBy { it.price }
                }
            }
            else if (sortMode == "Descending") {
                when(sortCategory){
                    "Name" -> sortedProducts = products.sortedByDescending { it.name }
                    "Brand" -> sortedProducts = products.sortedByDescending { it.brand_description }
                    "SubCategory" -> sortedProducts = products.sortedByDescending { it.subcategory_description }
                    "Price" -> sortedProducts = products.sortedByDescending { it.price }
                }
            }
        }
        return sortedProducts
    }

    override fun filterProducts(
        filters: HashMap<String, String>,
        products: List<Product>?
    ): List<Product>? {
        var filteredProducts = products
        filteredProducts = filterName(filters[Constants.Filters.FILTER_PRODUCTS_NAME],
            filteredProducts, products)
        filteredProducts = filterBrand(filters[Constants.Filters.FILTER_PRODUCTS_BRAND],
            filteredProducts, products)
        filteredProducts = filterSubCategory(filters[Constants.Filters.FILTER_PRODUCTS_SUBCATEGORY],
            filteredProducts, products)
        filteredProducts = filterPrice(filters[Constants.Filters.FILTER_PRODUCTS_PRICE],
            filteredProducts, products)
        filteredProducts = filterLink(filters[Constants.Filters.FILTER_PRODUCTS_LINK_TOKOPEDIA],
            Constants.ECommerces.ECOMMERCE_TOKOPEDIA, filteredProducts, products)
        filteredProducts = filterLink(filters[Constants.Filters.FILTER_PRODUCTS_LINK_BUKALAPAK],
            Constants.ECommerces.ECOMMERCE_BUKALAPAK, filteredProducts, products)
        filteredProducts = filterLink(filters[Constants.Filters.FILTER_PRODUCTS_LINK_SHOPEE],
            Constants.ECommerces.ECOMMERCE_SHOPEE, filteredProducts, products)

        return filteredProducts
    }

    /*************************************************************
     *                  PRIVATE FILTERS                          *
     *************************************************************/
    private fun filterName(name: String?, toFilter: List<Product>?, mainProducts: List<Product>?): List<Product>?{
        if(name == null) return toFilter
        return toFilter?.filter {
            it.name.contains(name.toString())
        } ?: mainProducts?.filter {
            it.name.contains(name.toString())
        }
    }

    private fun filterBrand(brand: String?, toFilter: List<Product>?, mainProducts: List<Product>?): List<Product>?{
        val con = MainActivity.getContext()
        if(brand == con.getString(R.string.all_brands)) return toFilter
        return toFilter?.filter {
            it.brand_description.contains(brand.toString())
        } ?: mainProducts?.filter {
            it.brand_description.contains(brand.toString())
        }
    }

    private fun filterSubCategory(subcat: String?, toFilter: List<Product>?, mainProducts: List<Product>?): List<Product>?{
        val con = MainActivity.getContext()
        if(subcat == con.getString(R.string.all_subcategory)) return toFilter
        return toFilter?.filter {
            it.subcategory_description.contains(subcat.toString())
        } ?: mainProducts?.filter {
            it.subcategory_description.contains(subcat.toString())
        }
    }

    private fun filterPrice(price: String?, toFilter: List<Product>?, mainProducts: List<Product>?): List<Product>? {
        if(price == null) return toFilter

        val prices = price.split(Constants.Separators.SEPARATOR_COMMA)
        val minPrice = prices.first().toInt()
        val maxPrice = prices.last().toInt()

        return toFilter?.filter {
            it.price in minPrice..maxPrice
        } ?: mainProducts?.filter {
            it.price in minPrice..maxPrice
        }
    }

    private fun filterLink(state: String?, link: String, toFilter: List<Product>?, mainProducts: List<Product>?): List<Product>? {
        if(state == Constants.States.CHECKBOX_UNCHECKED) return toFilter
        return when (link) {
            Constants.ECommerces.ECOMMERCE_TOKOPEDIA -> toFilter?.filter {
                !it.link_toped.isNullOrBlank()
            } ?: mainProducts?.filter {
                !it.link_toped.isNullOrBlank()
            }
            Constants.ECommerces.ECOMMERCE_BUKALAPAK -> toFilter?.filter {
                !it.link_bukalapak.isNullOrBlank()
            } ?: mainProducts?.filter {
                !it.link_bukalapak.isNullOrBlank()
            }
            Constants.ECommerces.ECOMMERCE_SHOPEE -> toFilter?.filter {
                !it.link_shopee.isNullOrBlank()
            } ?: mainProducts?.filter {
                !it.link_shopee.isNullOrBlank()
            }
            else -> toFilter
        }
    }
}