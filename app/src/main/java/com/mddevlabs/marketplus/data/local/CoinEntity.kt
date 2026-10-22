package com.mddevlabs.marketplus.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinEntity(
  @PrimaryKey
   val id: String,
  val symbol: String,
   val symbolName: String,
  val symbolImg: String,
   val currentPrice: Double,
   val marketCap: Double,
   val marketCapRank: Int,
   val totalVolume: Double ,
    val high24: Double,
    val low24: Double,
    val priceChangePercentage24H: Double,
    val ath: Double
)