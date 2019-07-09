package com.shandy.enterkomputermobileapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = false)
data class Product(@Json(name = "id") var id: Long,
                   @Json(name = "name") var name: String,
                   @Json(name = "details") var details: String,
                   @Json(name = "brand") var brand: Int,
                   @Json(name = "category") var category: String,
                   @Json(name = "subcategory") var subcategory: String?,
                   @Json(name = "brand_description") var brand_description: String,
                   @Json(name = "category_description") var category_description: String,
                   @Json(name = "subcategory_description") var subcategory_description: String,
                   @Json(name = "price") var price: Int,
                   @Json(name = "weight") var weight: Double,
                   @Json(name = "quantity") var quantity: Int,
                   @Json(name = "stock_type") var stock_type: Int,
                   @Json(name = "link_toped") var link_toped: String?,
                   @Json(name = "link_shopee") var link_shopee: String?,
                   @Json(name = "link_bukalapak") var link_bukalapak: String?)