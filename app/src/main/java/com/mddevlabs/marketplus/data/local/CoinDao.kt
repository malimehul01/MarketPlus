package com.mddevlabs.marketplus.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Upsert
    suspend fun upsertAll(coins: List<CoinEntity>)

    @Query("SELECT * FROM coins ORDER BY marketCapRank ASC")
    fun getAllcoins(): Flow<List<CoinEntity>>

    @Query("SELECT * FROM COINS WHERE id=:coinId")
    fun getCoinById(coinId: String): Flow<CoinEntity>
}
