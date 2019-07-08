package com.shandy.enterkomputermobileapp.utils.string

import java.text.NumberFormat
import java.util.*

object CurrencyFormatter {

    fun getUSDCurrencyFormat(value: Int): String {
        val formatter = NumberFormat.getNumberInstance(Locale.US)
        return "$${formatter.format(value)}"
    }

    fun getIDRCurrencyFormat(value: Int): String {
        val localeID = Locale("in", "ID")
        val formatter = NumberFormat.getNumberInstance(localeID)
        return "Rp. ${formatter.format(value)}"
    }
}