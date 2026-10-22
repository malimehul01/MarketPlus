package com.mddevlabs.marketplus.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mddevlabs.marketplus.data.local.CoinEntity
import com.mddevlabs.marketplus.data.repository.CoinRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CoinDetailViewmodel(
    private val repository: CoinRepository,
    private val coinId: String
): ViewModel(){

     val coin: StateFlow<CoinEntity?> =repository.getCoinById(coinId).stateIn(
         scope = viewModelScope,
         started = SharingStarted.WhileSubscribed(5000),
         initialValue =null,
     )
}