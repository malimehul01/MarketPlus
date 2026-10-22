package com.mddevlabs.marketplus.presentation
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mddevlabs.marketplus.data.local.CoinEntity
import com.mddevlabs.marketplus.data.repository.CoinRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CoinViewModel(
    private  val  repository: CoinRepository,

): ViewModel(){

  val coins: StateFlow<List<CoinEntity>> =repository.getCoins().stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList(),
  )


    var isLoading by mutableStateOf(false)
        private set
    var erorMessage by mutableStateOf<String?>(null)
        private set


    init {
        refreshData()
    }
    fun refreshData(){
        viewModelScope.launch {
            while (isActive) {
                if(coins.value.isEmpty()){
                    isLoading = true
                }
                erorMessage = null

                val result = repository.refreshCoins()
                isLoading = false
                result.onFailure { error ->
                    erorMessage = error.localizedMessage ?: "Network error occurred"
                }

                delay(30000L)
            }
        }
    }
}