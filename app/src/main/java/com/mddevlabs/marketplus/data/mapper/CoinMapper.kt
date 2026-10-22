package com.mddevlabs.marketplus.data.mapper

import com.mddevlabs.marketplus.data.local.CoinEntity
import com.mddevlabs.marketplus.data.remote.dto.CoinDto

fun CoinDto.toEntity(): CoinEntity{
    return CoinEntity(
        id = id,
        symbol = symbol,
        symbolName = symbolName,
        symbolImg = symbolImg,
        currentPrice =currentPrice?:0.0,
        marketCap =marketCap?:0.0,
        marketCapRank = marketCapRank?:0,
        totalVolume =totalVolume?:0.0,
        high24 = high24?:0.0,
        low24 =low24?:0.0,
        priceChangePercentage24H =priceChangePercentage24H?:0.0,
        ath = ath?:0.0,
    )
}