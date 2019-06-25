package com.shandy.enterkomputermobileapp.api

import android.util.Log
import com.shandy.enterkomputermobileapp.models.Product
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.net.URL

class Request {

    fun getProduct(url: String) : List<Product>? {
        /* Get Response */
        val responseStr = URL(url).readText()

        /* Createg adapter for JSON ARRAY */
        val listType = Types.newParameterizedType(List::class.java, Product::class.java)
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Product>> = moshi.adapter(listType)
        val result = adapter.fromJson(responseStr)
        Log.d("TEST API", result.toString())
        return result
    }

}
