package com.shandy.enterkomputermobileapp.repository

import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.network.Endpoints
import com.shandy.enterkomputermobileapp.network.RetrofitClient
import com.shandy.enterkomputermobileapp.presentation.MainActivity
import com.shandy.enterkomputermobileapp.utils.Constants

class ProductRemoteDataSource : ProductData.RemoteDataSource {

    private val webServices = RetrofitClient
        .getInstance(Constants.URLS.URL_PRODUCT_BASE)
        .create(Endpoints.ProductEndpoints::class.java)

    override fun loadProducts(category: String): List<Product>? {
        val con = MainActivity.getContext()
        return when(category){
            con.getString(R.string.product_accessories) -> webServices.getListAccessories().execute().body()
            con.getString(R.string.product_aio) -> webServices.getListAIO().execute().body()
            con.getString(R.string.product_casing) -> webServices.getListCasing().execute().body()
            con.getString(R.string.product_cooler) -> webServices.getListCoolerFan().execute().body()
            con.getString(R.string.product_drawing) -> webServices.getListDrawingTablet().execute().body()
            con.getString(R.string.product_drone) -> webServices.getListDrone().execute().body()
            con.getString(R.string.product_fd) -> webServices.getListFlashDrive().execute().body()
            con.getString(R.string.product_gadget) -> webServices.getListGadget().execute().body()
            con.getString(R.string.product_hdd) -> webServices.getListHDD().execute().body()
            con.getString(R.string.product_headset) -> webServices.getListHeadset().execute().body()
            con.getString(R.string.product_keyboard) -> webServices.getListKeyboard().execute().body()
            con.getString(R.string.product_lcd) -> webServices.getListLCD().execute().body()
            con.getString(R.string.product_sdcard) -> webServices.getListSDCard().execute().body()
            con.getString(R.string.product_motherboard) -> webServices.getListMotherboard().execute().body()
            con.getString(R.string.product_networking) -> webServices.getListNetworking().execute().body()
            con.getString(R.string.product_notebook) -> webServices.getListNotebook().execute().body()
            con.getString(R.string.product_optical) -> webServices.getListOptical().execute().body()
            con.getString(R.string.product_printer) -> webServices.getListPrinter().execute().body()
            con.getString(R.string.product_processor) -> webServices.getListProcessor().execute().body()
            con.getString(R.string.product_projector) -> webServices.getListProjector().execute().body()
            con.getString(R.string.product_psu) -> webServices.getListPSU().execute().body()
            con.getString(R.string.product_ram) -> webServices.getListRAM().execute().body()
            con.getString(R.string.product_server) -> webServices.getListServer().execute().body()
            con.getString(R.string.product_software) -> webServices.getListSoftware().execute().body()
            con.getString(R.string.product_ssd) -> webServices.getListSSD().execute().body()
            con.getString(R.string.product_soundcard) -> webServices.getListSoundcard().execute().body()
            con.getString(R.string.product_speaker) -> webServices.getListSpeaker().execute().body()
            con.getString(R.string.product_ups) -> webServices.getListUPS().execute().body()
            con.getString(R.string.product_vga) -> webServices.getListVGA().execute().body()
            else -> null
        }?.sortedBy { it.name }
    }
}