package com.br.mercadobitcoin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


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

fun dateFormat(timestamp:Int): String {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    val time = Date(timestamp.toLong() * 1000)
    val dateTime = SimpleDateFormat("dd/MM/YYYY").format(time)
    return dateTime
}

fun currencyFormat(valor:Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    format.setCurrency(Currency.getInstance("BRL"))
    return format.format(valor)
}