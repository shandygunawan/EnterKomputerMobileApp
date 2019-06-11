package com.shandy.enterkomputermobileapp.network

import com.shandy.enterkomputermobileapp.models.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductEndpoints {

    @GET("accessories.json")
    fun getListAccessories(): Call<List<Product>>

    @GET("allinone.json")
    fun getListAIO(): Call<List<Product>>

    @GET("casing.json")
    fun getListCasing(): Call<List<Product>>

    @GET("coolerfan.json")
    fun getListCoolerFan(): Call<List<Product>>

    @GET("drawing.json")
    fun getListDrawingTablet(): Call<List<Product>>

    @GET("drone.json")
    fun getListDrone(): Call<List<Product>>

    @GET("flashdisk.json")
    fun getListFlashdisk(): Call<List<Product>>

    @GET("gadget.json")
    fun getListGadget(): Call<List<Product>>

    @GET("harddisk.json")
    fun getListHarddisk(): Call<List<Product>>
}