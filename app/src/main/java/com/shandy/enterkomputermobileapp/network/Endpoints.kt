package com.shandy.enterkomputermobileapp.network

import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {
    interface ProductEndpoints {

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_ACCESSORIES)
        fun getListAccessories(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_AIO)
        fun getListAIO(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_CASING)
        fun getListCasing(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_COOLER)
        fun getListCoolerFan(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_DRAWING)
        fun getListDrawingTablet(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_DRONE)
        fun getListDrone(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_FLASHDRIVE)
        fun getListFlashdisk(): Call<List<Product>>

        @GET("gadget.json")
        fun getListGadget(): Call<List<Product>>

        @GET("harddisk.json")
        fun getListHarddisk(): Call<List<Product>>
    }
}