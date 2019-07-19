package com.shandy.enterkomputermobileapp.repository

import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.network.Endpoints
import com.shandy.enterkomputermobileapp.network.RetrofitClient
import com.shandy.enterkomputermobileapp.utils.Constants

class ProductRemoteDataSource : ProductData.RemoteDataSource {

    private val webServices = RetrofitClient
        .getInstance(Constants.URLS.URL_PRODUCT_BASE)
        .create(Endpoints.ProductEndpoints::class.java)

    override fun loadProducts(category: String): List<Product>? {
        return when(category){
            Constants.Products.PRODUCT_ACCESSORIES -> webServices.getListAccessories().execute().body()
            Constants.Products.PRODUCT_AIO -> webServices.getListAIO().execute().body()
            Constants.Products.PRODUCT_CASING -> webServices.getListCasing().execute().body()
            Constants.Products.PRODUCT_COOLER -> webServices.getListCoolerFan().execute().body()
            Constants.Products.PRODUCT_DRAWING -> webServices.getListDrawingTablet().execute().body()
            Constants.Products.PRODUCT_DRONE -> webServices.getListDrone().execute().body()
            Constants.Products.PRODUCT_FLASHDRIVE -> webServices.getListFlashDrive().execute().body()
            Constants.Products.PRODUCT_GADGET -> webServices.getListGadget().execute().body()
            Constants.Products.PRODUCT_HDD -> webServices.getListHDD().execute().body()
            Constants.Products.PRODUCT_HEADSET -> webServices.getListHeadset().execute().body()
            Constants.Products.PRODUCT_KEYBOARD -> webServices.getListKeyboard().execute().body()
            Constants.Products.PRODUCT_LCD -> webServices.getListLCD().execute().body()
            Constants.Products.PRODUCT_SDCARD -> webServices.getListSDCard().execute().body()
            Constants.Products.PRODUCT_MOTHERBOARD -> webServices.getListMotherboard().execute().body()
            Constants.Products.PRODUCT_NETWORKING -> webServices.getListNetworking().execute().body()
            Constants.Products.PRODUCT_NOTEBOOK -> webServices.getListNotebook().execute().body()
            Constants.Products.PRODUCT_OPTICAL -> webServices.getListOptical().execute().body()
            Constants.Products.PRODUCT_PRINTER -> webServices.getListPrinter().execute().body()
            Constants.Products.PRODUCT_PROCESSOR -> webServices.getListProcessor().execute().body()
            Constants.Products.PRODUCT_PROJECTOR -> webServices.getListProjector().execute().body()
            Constants.Products.PRODUCT_PSU -> webServices.getListPSU().execute().body()
            Constants.Products.PRODUCT_RAM -> webServices.getListRAM().execute().body()
            Constants.Products.PRODUCT_SERVER -> webServices.getListServer().execute().body()
            Constants.Products.PRODUCT_SOFTWARE -> webServices.getListSoftware().execute().body()
            Constants.Products.PRODUCT_SSD -> webServices.getListSSD().execute().body()
            Constants.Products.PRODUCT_SOUNDCARD -> webServices.getListSoundcard().execute().body()
            Constants.Products.PRODUCT_SPEAKER -> webServices.getListSpeaker().execute().body()
            Constants.Products.PRODUCT_UPS -> webServices.getListUPS().execute().body()
            Constants.Products.PRODUCT_VGA -> webServices.getListVGA().execute().body()
            else -> null
        }
    }
}