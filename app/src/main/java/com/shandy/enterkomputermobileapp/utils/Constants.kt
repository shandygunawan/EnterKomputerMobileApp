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
        const val URL_HOWTO_WEBSITE = "https://www.enterkomputer.com/assets/img/how-to-enterx.png"
        const val URL_HOWTO_WAEMAIL = "https://www.enterkomputer.com/assets/img/howto2x.jpg"
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
            R.drawable.icon_accessories_black,
            R.drawable.icon_aio_black,
            R.drawable.icon_casing_black,
            R.drawable.icon_cooler_black,
            R.drawable.icon_drawing_black,
            R.drawable.icon_drone_black,
            R.drawable.icon_flashdisk_black
        )
    }

    object Filters {
        const val FILTER_PRODUCTS_NAME = "filter_products_name"
        const val FILTER_PRODUCTS_BRAND = "filter_products_brand"
        const val FILTER_PRODUCTS_SUBCATEGORY = "filter_products_subcategory"
        const val FILTER_PRODUCTS_PRICE = "filter_products_price"
        const val FILTER_PRODUCTS_WEIGHT = "filter_products_weight"
    }

}

