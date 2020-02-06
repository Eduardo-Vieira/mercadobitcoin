package com.br.mercadobitcoin.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Ticker {
    @SerializedName("high")
    @Expose
    var high: Double? = null
    @SerializedName("low")
    @Expose
    var low: Double? = null
    @SerializedName("vol")
    @Expose
    var vol: Double? = null
    @SerializedName("last")
    @Expose
    var last: Double? = null
    @SerializedName("buy")
    @Expose
    var buy: Double? = null
    @SerializedName("sell")
    @Expose
    var sell: Double? = null
    @SerializedName("date")
    @Expose
    var date: Int? = null
}