package com.br.mercadobitcoin.utils

import android.annotation.SuppressLint
import com.br.mercadobitcoin.enumeration.EnumCoin
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun dateFormat(timestamp: Int): String {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    val time = Date(timestamp.toLong() * 1000)
    return SimpleDateFormat("dd/MM/yyyy").format(time)
}

fun currencyFormat(valor:Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    format.currency = Currency.getInstance("BRL")
    return format.format(valor)
}

fun setBitTitle(id: String):String {
    return when(id) {
        EnumCoin.BTC.name -> "Bitcoin"
        EnumCoin.LTC.name -> "Litecoin"
        EnumCoin.BCH.name -> "Bitcoin Cash"
        EnumCoin.XRP.name -> "XRP"
        EnumCoin.ETH.name -> "Ethereum"
        else -> ""
    }

}