package com.br.mercadobitcoin.ui.fragment.home.viewmodel.state

import com.br.mercadobitcoin.database.entity.Ticker

sealed class TickerListViewState {
    data class GetTickerSuccess(val tickerModel: ArrayList<Ticker>): TickerListViewState()
    data class GetTickerEmpty(val tickerModel: List<Ticker>): TickerListViewState()
    data class GetTickerError(val tickerModel: List<Ticker>):TickerListViewState()
}