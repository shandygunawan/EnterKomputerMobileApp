package com.shandy.enterkomputermobileapp.utils

import com.shandy.enterkomputermobileapp.R

object Constants {

    object Endpoints {
        const val ENDPOINT_PRODUCT_ACCESSORIES = "accessories.json"
        const val ENDPOINT_PRODUCT_AIO = "allinone.json"
        const val ENDPOINT_PRODUCT_CASING = "casing.json"
        const val ENDPOINT_PRODUCT_COOLER = "coolerfan.json"
        const val ENDPOINT_PRODUCT_DRAWING = "drawing.json"
        const val ENDPOINT_PRODUCT_DRONE = "drone.json"
        const val ENDPOINT_PRODUCT_FLASHDISK = "flashdisk.json"
    }

    /* URL */
    object URLS {
        const val URL_PRODUCT_BASE = "https://enterkomputer.com/api/product/"
        const val URL_PRODUCT_ACCESSORIES = URL_PRODUCT_BASE + "accessories.json"
        const val URL_PRODUCT_NOTEBOOK = URL_PRODUCT_BASE + "notebook.json"
    }

    object Products {
        const val PRODUCT_ACCESSORIES = "accessories"
        const val PRODUCT_AIO = "allinone"
        const val PRODUCT_CASING = "casing"
        const val PRODUCT_COOLER = "cooler"
        const val PRODUCT_DRAWING = "drawing"
        const val PRODUCT_DRONE = "drone"
        const val PRODUCT_FLASHDISK = "flashdisk"
    }

    object ECommerces {
        const val ECOMMERCE_TOKOPEDIA = "Tokopedia"
        const val ECOMMERCE_BUKALAPAK = "Bukalapak"
        const val ECOMMERCE_SHOPEE = "Shopee"
    }

    object Navigation {
        const val NAVIGATION_HOME = "home"
        const val NAVIGATION_PRODUCTS = "products"
        const val NAVIGATION_SIMULATION = "simulation"
        const val NAVIGATION_HOW_TO = "how_to"
        const val NAVIGATION_TIPS = "tips"
        const val NAVIGATION_ORDER_TRACKING = "order_tracking"
    }

    object Lists {
        val LIST_PRODUCTS = listOf(
            R.drawable.icon_accessories_bw,
            R.drawable.icon_aio_bw,
            R.drawable.icon_casing_bw,
            R.drawable.icon_cooler_bw,
            R.drawable.icon_drawing_bw,
            R.drawable.icon_drone_bw,
            R.drawable.icon_flashdisk_bw
        )
    }

}

