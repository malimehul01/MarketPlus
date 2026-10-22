package com.mddevlabs.marketplus.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class CoinDto (
    val id: String,

    @SerialName("symbol")
    val symbol : String,

    @SerialName("name")
    val symbolName: String,

    @SerialName("image")
    val symbolImg: String,

    @SerialName("current_price")
    val currentPrice: Double?=null,

    @SerialName("market_cap")
    val marketCap: Double?=null,

    @SerialName("market_cap_rank")
    val marketCapRank: Int?=null,

    @SerialName("total_volume")
    val totalVolume: Double?=null,

    @SerialName("high_24h")
    val high24: Double?=null,

    @SerialName("low_24h")
    val low24: Double?=null,

    @SerialName("price_change_percentage_24h")
    val priceChangePercentage24H: Double?=null,

    @SerialName("ath")
    val ath: Double?=null

)