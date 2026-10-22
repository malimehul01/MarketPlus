package com.mddevlabs.marketplus.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

class InternetObserver(
    context: Context,
    private val onInternetAvailable: () -> Unit,
    private val onInternetLost:()-> Unit

) {

    private val cm =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            ) {

                onInternetAvailable()
            }

        }

        override fun onLost(network: Network) {
            super.onLost(network)
            onInternetLost()
        }




    }


    fun register() {
        cm.registerDefaultNetworkCallback(networkCallback)
    }
    fun unregister(){
        try {
            cm.unregisterNetworkCallback(networkCallback)
        }catch (e: Exception){

        }
    }
}