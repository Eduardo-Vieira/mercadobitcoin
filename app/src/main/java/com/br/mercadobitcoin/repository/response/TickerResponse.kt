package com.br.mercadobitcoin.repository.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TickerResponse(
    @SerializedName("ticker")
    @Expose
    var ticker: Ticker
)