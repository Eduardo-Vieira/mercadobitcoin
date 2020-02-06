package com.br.mercadobitcoin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build


@Suppress("DEPRECATION")
fun isConnected(cont:Context):Boolean {
    (cont.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getNetworkCapabilities(activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
            } ?: false
        } else {
            val networkInfo: NetworkInfo? = activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}
