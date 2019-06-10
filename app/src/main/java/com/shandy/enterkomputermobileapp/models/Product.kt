package com.shandy.enterkomputermobileapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@JsonClass (generateAdapter = false)
@Entity
data class Product(@Json(name = "id") @Id var id: Long,
                   @Json(name = "name") val name: String,
                   @Json(name = "details") val details: String,
                   @Json(name = "brand") val brand: String,
                   @Json(name = "category") val category: String,
                   @Json(name = "subcategory") val subcategory: String,
                   @Json(name = "brand_description") val brand_description: String,
                   @Json(name = "category_description") val category_description: String,
                   @Json(name = "subcategory_description") val subcategory_description: String,
                   @Json(name = "price") val price: String,
                   @Json(name = "weight") val weight: String,
                   @Json(name = "quantity") val quantity: String,
                   @Json(name = "stock_type") val stock_type: String,
                   @Json(name = "link_toped") val link_toped: String?,
                   @Json(name = "link_shopee") val link_shopee: String?,
                   @Json(name = "link_bukalapak") val link_bukalapak: String?)