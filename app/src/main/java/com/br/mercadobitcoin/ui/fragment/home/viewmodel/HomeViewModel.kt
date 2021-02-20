package com.br.mercadobitcoin.ui.fragment.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.mercadobitcoin.interactor.HomeInteractorImp
import com.br.mercadobitcoin.ui.fragment.home.HomeFragment
import com.br.mercadobitcoin.ui.fragment.home.viewmodel.state.TickerListViewState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeInteractor: HomeInteractorImp
) : ViewModel() {

    private val listState = MutableLiveData<TickerListViewState>()
    val listViewState:LiveData<TickerListViewState> = listState

    fun getTicker(){
       viewModelScope.launch {
           try {
               val tickerList = homeInteractor.getTickerList()

               if (tickerList.isEmpty()){
                   val localTickerList = homeInteractor.getLocalTicker()
                   listState.value = TickerListViewState.GetTickerEmpty(localTickerList)
               } else {
                   listState.value = TickerListViewState.GetTickerSuccess(tickerList)
               }
           } catch (ex:Throwable){
               val localTickerList = homeInteractor.getLocalTicker()
               listState.value = TickerListViewState.GetTickerError(localTickerList)
           }
       }
    }

    fun showTickerList(state: TickerListViewState, fragment: HomeFragment){
        when(state){
            is TickerListViewState.GetTickerSuccess -> {
                val list = state.tickerModel
                fragment.adapterUpdate(list)
            }
            is TickerListViewState.GetTickerEmpty -> {
                val list = state.tickerModel
                fragment.adapterUpdate(list)
            }
            is TickerListViewState.GetTickerError -> {
                fragment.showError()
                val list = state.tickerModel
                fragment.adapterUpdate(list)
            }
        }

    }
}

