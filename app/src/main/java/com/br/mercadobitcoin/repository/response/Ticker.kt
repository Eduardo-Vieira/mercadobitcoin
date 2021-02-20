package com.br.mercadobitcoin.repository.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Ticker(
    @SerializedName("high")
    @Expose
    val high: Double,
    @SerializedName("low")
    @Expose
    val low: Double,
    @SerializedName("vol")
    @Expose
    val vol: Double,
    @SerializedName("last")
    @Expose
    val last: Double,
    @SerializedName("buy")
    @Expose
    val buy: Double,
    @SerializedName("sell")
    @Expose
    val sell: Double,
    @SerializedName("date")
    @Expose
    val date: Int
)