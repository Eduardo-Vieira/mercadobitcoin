package com.br.mercadobitcoin.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TickerResponse {
    @SerializedName("ticker")
    @Expose
    var ticker: Ticker? = null
}