package com.mddevlabs.marketplus.data.repository

import com.mddevlabs.marketplus.data.local.CoinDao
import com.mddevlabs.marketplus.data.local.CoinEntity
import com.mddevlabs.marketplus.data.mapper.toEntity
import com.mddevlabs.marketplus.data.remote.CoinGeckoApi
import kotlinx.coroutines.flow.Flow
import okio.IOException

class CoinRepositoryImpl (
    private val api: CoinGeckoApi,
        private val  dao: CoinDao

    ): CoinRepository{
        override fun getCoins(): Flow<List<CoinEntity>> {
            return dao.getAllcoins()
        }

    override fun getCoinById(coinId: String): Flow<CoinEntity> {
         return dao.getCoinById(coinId)
    }

    override suspend fun refreshCoins(): Result<Unit> {
           return try {
               val coinList=api.getCoinMarkets()
               val entities=coinList.map { it.toEntity()}
               dao.upsertAll(entities)
               Result.success(Unit)
           }catch(e: Exception){
               Result.failure(e)
           }

        }
     }


