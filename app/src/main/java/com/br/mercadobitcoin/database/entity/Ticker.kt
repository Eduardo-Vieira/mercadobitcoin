package com.br.mercadobitcoin.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticker")
data class Ticker(
    @PrimaryKey
    @ColumnInfo(name = "id")   var id: String,
    @ColumnInfo(name = "high") var high: Double,
    @ColumnInfo(name = "low")  var low: Double,
    @ColumnInfo(name = "vol")  var vol: Double,
    @ColumnInfo(name = "last") var last: Double,
    @ColumnInfo(name = "buy")  var buy: Double,
    @ColumnInfo(name = "sell") var sell: Double,
    @ColumnInfo(name = "date") var date: Int
)