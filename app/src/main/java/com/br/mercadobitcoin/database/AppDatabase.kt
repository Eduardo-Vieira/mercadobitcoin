package com.br.mercadobitcoin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.br.mercadobitcoin.database.dao.TickerDao
import com.br.mercadobitcoin.database.entity.Ticker

@Database(entities = [
Ticker::class
], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tickerDao(): TickerDao
}