package com.mddevlabs.marketplus.data.repository

import com.mddevlabs.marketplus.data.local.CoinEntity
import kotlinx.coroutines.flow.Flow

interface CoinRepository{

    fun getCoins(): Flow<List<CoinEntity>>
    fun getCoinById(coinId: String): Flow<CoinEntity>
    suspend fun  refreshCoins(): Result<Unit>
}