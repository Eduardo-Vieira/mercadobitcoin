package com.br.mercadobitcoin.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.br.mercadobitcoin.database.entity.Ticker

@Dao
interface TickerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(ticker: List<Ticker>)

    @Update
    suspend fun update(ticker: Ticker)

    @Delete
    suspend fun delete(ticker: Ticker)

    @Query("SELECT * FROM ticker")
    suspend fun getAll(): List<Ticker>
}