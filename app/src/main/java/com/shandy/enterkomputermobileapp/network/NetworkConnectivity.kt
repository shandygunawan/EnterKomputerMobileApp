package com.shandy.enterkomputermobileapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager

class NetworkConnectivity {

    private lateinit var context: Context
    private lateinit var cm : ConnectivityManager
    private var activeNetwork: NetworkInfo? = null

    fun getNetworkInfo(paramContext: Context){
        this.context = paramContext
        this.cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        this.activeNetwork= cm.activeNetworkInfo
    }

    fun isOnline(): Boolean = activeNetwork?.isConnected == true

    fun isWifi(): Boolean = activeNetwork?.type == ConnectivityManager.TYPE_WIFI

    fun isMobile(): Boolean = activeNetwork?.type == ConnectivityManager.TYPE_MOBILE

    fun isMobileFast() : Boolean {
        val teleManager: TelephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        return when(teleManager.networkType){
            TelephonyManager.NETWORK_TYPE_GPRS -> false
            TelephonyManager.NETWORK_TYPE_EDGE -> false
            TelephonyManager.NETWORK_TYPE_CDMA -> false
            TelephonyManager.NETWORK_TYPE_GSM -> false
            TelephonyManager.NETWORK_TYPE_UNKNOWN -> false
            else -> true
        }
    }
}