package com.mddevlabs.marketplus.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun isInternetAvailable(context: Context): Boolean {
    val cm=context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    val network=cm?.activeNetwork?:return false
    val capabilities=cm.getNetworkCapabilities(network)?:return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)&& capabilities.hasCapability(
        NetworkCapabilities.NET_CAPABILITY_VALIDATED)
}