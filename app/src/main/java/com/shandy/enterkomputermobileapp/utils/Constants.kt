package com.shandy.enterkomputermobileapp.utils

import android.content.res.Resources
import com.shandy.enterkomputermobileapp.R

object Constants {

    object Endpoints {
        const val ENDPOINT_PRODUCT_ACCESSORIES = "accessories.json"
        const val ENDPOINT_PRODUCT_AIO = "allinone.json"
        const val ENDPOINT_PRODUCT_CASING = "casing.json"
        const val ENDPOINT_PRODUCT_COOLER = "coolerfan.json"
        const val ENDPOINT_PRODUCT_DRAWING = "drawing.json"
        const val ENDPOINT_PRODUCT_DRONE = "drone.json"
        const val ENDPOINT_PRODUCT_FLASHDRIVE = "flashdisk.json"
        const val ENDPOINT_PRODUCT_GADGET = "gadget.json"
        const val ENDPOINT_PRODUCT_HDD = "harddisk.json"
        const val ENDPOINT_PRODUCT_HEADSET = "headset.json"
        const val ENDPOINT_PRODUCT_KEYBOARD = "keyboard.json"
        const val ENDPOINT_PRODUCT_LCD = "lcd.json"
        const val ENDPOINT_PRODUCT_SDCARD = "memorycard.json"
        const val ENDPOINT_PRODUCT_MOTHERBOARD = "motherboard.json"
        const val ENDPOINT_PRODUCT_NETWORKING = "networking.json"
        const val ENDPOINT_PRODUCT_NOTEBOOK = "notebook.json"
        const val ENDPOINT_PRODUCT_OPTICAL = "optical.json"
        const val ENDPOINT_PRODUCT_PRINTER = "printer.json"
        const val ENDPOINT_PRODUCT_PROCESSOR = "processor.json"
        const val ENDPOINT_PRODUCT_PROJECTOR = "projector.json"
        const val ENDPOINT_PRODUCT_PSU = "psu.json"
        const val ENDPOINT_PRODUCT_RAM = "memoryram.json"
        const val ENDPOINT_PRODUCT_SERVER = "server.json"
        const val ENDPOINT_PRODUCT_SOFTWARE = "software.json"
        const val ENDPOINT_PRODUCT_SOUNDCARD = "soundcard.json"
        const val ENDPOINT_PRODUCT_SPEAKER = "speaker.json"
        const val ENDPOINT_PRODUCT_SSD = "ssd.json"
        const val ENDPOINT_PRODUCT_UPS = "ups.json"
        const val ENDPOINT_PRODUCT_VGA = "vga.json"
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
        const val PRODUCT_ACCESSORIES = "Accessories"
        const val PRODUCT_AIO = "All in One"
        const val PRODUCT_CASING = "Casing"
        const val PRODUCT_COOLER = "Cooler"
        const val PRODUCT_DRAWING = "Drawing"
        const val PRODUCT_DRONE = "Drone"
        const val PRODUCT_FLASHDRIVE = "Flash Drive"
        const val PRODUCT_GADGET = "Gadget"
        const val PRODUCT_HDD = "Hard Drive"
        const val PRODUCT_HEADSET = "Headset"
        const val PRODUCT_KEYBOARD = "Keyboard"
        const val PRODUCT_LCD = "LCD"
        const val PRODUCT_SDCARD = "Memory Card"
        const val PRODUCT_MOTHERBOARD = "Motherboard"
        const val PRODUCT_NETWORKING = "Networking"
        const val PRODUCT_NOTEBOOK = "Notebook"
        const val PRODUCT_OPTICAL = "Optical"
        const val PRODUCT_PRINTER = "Printer"
        const val PRODUCT_PROCESSOR = "Processor"
        const val PRODUCT_PROJECTOR = "Projector"
        const val PRODUCT_PSU = "PSU"
        const val PRODUCT_RAM = "RAM"
        const val PRODUCT_SERVER = "Server"
        const val PRODUCT_SOFTWARE = "Software"
        const val PRODUCT_SSD = "SSD"
        const val PRODUCT_SOUNDCARD = "Sound Card"
        const val PRODUCT_SPEAKER = "Speaker"
        const val PRODUCT_UPS = "UPS"
        const val PRODUCT_VGA = "VGA"
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
            R.drawable.icon_product_category_others_colored,
            R.drawable.icon_product_category_aio_colored,
            R.drawable.icon_product_category_casing_colored,
            R.drawable.icon_product_category_cooler_colored,
            R.drawable.icon_product_category_drawing_colored,
            R.drawable.icon_product_category_drone_colored,
            R.drawable.icon_product_category_fd_colored,
            R.drawable.icon_product_category_smartphone_colored,
            R.drawable.icon_product_category_hdd_colored,
            R.drawable.icon_product_category_headset_colored,
            R.drawable.icon_product_category_keyboard_colored,
            R.drawable.icon_product_category_lcd_colored,
            R.drawable.icon_product_category_sdcard_colored,
            R.drawable.icon_product_category_motherboard_colored,
            R.drawable.icon_product_category_networking_colored,
            R.drawable.icon_product_category_notebook_colored,
            R.drawable.icon_product_category_optical_colored,
            R.drawable.icon_product_category_printer_colored,
            R.drawable.icon_product_category_processor_colored,
            R.drawable.icon_product_category_projector_colored,
            R.drawable.icon_product_category_psu_colored,
            R.drawable.icon_product_category_ram_colored,
            R.drawable.icon_product_category_server_colored,
            R.drawable.icon_product_category_software_colored,
            R.drawable.icon_product_category_ssd_colored,
            R.drawable.icon_product_category_soundcard_colored,
            R.drawable.icon_product_category_speaker_colored,
            R.drawable.icon_product_category_ups_colored,
            R.drawable.icon_product_category_vga_colored
        )
    }

    object Filters {
        const val FILTER_PRODUCTS_NAME = "filter_products_name"
        const val FILTER_PRODUCTS_BRAND = "filter_products_brand"
        const val FILTER_PRODUCTS_SUBCATEGORY = "filter_products_subcategory"
        const val FILTER_PRODUCTS_PRICE = "filter_products_price"
        const val FILTER_PRODUCTS_WEIGHT = "filter_products_weight"
        const val FILTER_PRODUCTS_LINK_BUKALAPAK = "filter_products_link_bukalapak"
        const val FILTER_PRODUCTS_LINK_TOKOPEDIA = "filter_products_link_tokopedia"
        const val FILTER_PRODUCTS_LINK_SHOPEE = "filter_products_link_shopee"
        const val FILTER_PRODUCTS_ALLSUBCATEGORY = "All SubCategory"
    }

    object Sort {
        const val SORT_PRODUCT_NAME = "Name"
        const val SORT_PRODUCT_BRAND = "Brand"
        const val SORT_PRODUCT_SUBCATEGORY = "Subcategory"
        const val SORT_PRODUCT_PRICE = "Price"
        const val SORT_MODE_ASC = "Ascending"
        const val SORT_MODE_DESC = "Descending"
    }

    object States {
        const val CHECKBOX_CHECKED = "checkbox_checked"
        const val CHECKBOX_UNCHECKED = "checkbox_unchecked"
    }

    object Strings {
        const val STRING_EMPTY = ""
    }

    object Separators {
        const val SEPARATOR_COMMA = ","
        const val SEPARATOR_SLASH = "/"
    }

    object Currency {
        const val CURRENCY_YUAN = "CNY"
        const val CURRENCY_DOLLAR_AUS = "UD"
        const val CURRENCY_DOLLAR_US = "USD"
        const val CURRENCY_EURO = "EUR"
        const val CURRENCY_RUPIAH = "IDR"
        const val CURRENCY_YEN = "JPY"
    }
}

