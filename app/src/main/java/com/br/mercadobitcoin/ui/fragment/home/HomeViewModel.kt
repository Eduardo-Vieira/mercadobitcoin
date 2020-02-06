package com.br.mercadobitcoin.ui.fragment.home

import androidx.lifecycle.ViewModel
import com.br.mercadobitcoin.repository.HomeRepository

class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {
    fun getTicker() = homeRepository.getTicker()
}

