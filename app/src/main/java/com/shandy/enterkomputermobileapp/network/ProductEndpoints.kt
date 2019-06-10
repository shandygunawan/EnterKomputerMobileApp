package com.shandy.enterkomputermobileapp.network

import com.shandy.enterkomputermobileapp.models.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductEndpoints {

    @GET("accessories.json")
    fun getListAccessories(): Call<List<Product>>

    @GET("allinone.json")
    fun getListAIO(): Call<List<Product>>
}