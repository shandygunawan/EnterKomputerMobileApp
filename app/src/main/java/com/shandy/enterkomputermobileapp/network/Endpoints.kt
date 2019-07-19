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
        fun getListFlashDrive(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_GADGET)
        fun getListGadget(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_HDD)
        fun getListHDD(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_HEADSET)
        fun getListHeadset(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_KEYBOARD)
        fun getListKeyboard(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_LCD)
        fun getListLCD(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_SDCARD)
        fun getListSDCard(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_MOTHERBOARD)
        fun getListMotherboard(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_NETWORKING)
        fun getListNetworking(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_NOTEBOOK)
        fun getListNotebook(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_OPTICAL)
        fun getListOptical(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_PRINTER)
        fun getListPrinter(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_PROCESSOR)
        fun getListProcessor(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_PROJECTOR)
        fun getListProjector(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_PSU)
        fun getListPSU(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_RAM)
        fun getListRAM(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_SERVER)
        fun getListServer(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_SOFTWARE)
        fun getListSoftware(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_SOUNDCARD)
        fun getListSoundcard(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_SPEAKER)
        fun getListSpeaker(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_SSD)
        fun getListSSD(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_UPS)
        fun getListUPS(): Call<List<Product>>

        @GET(Constants.Endpoints.ENDPOINT_PRODUCT_VGA)
        fun getListVGA(): Call<List<Product>>
    }
}