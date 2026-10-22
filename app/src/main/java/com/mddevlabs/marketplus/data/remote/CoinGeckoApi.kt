package com.mddevlabs.marketplus.data.remote

import com.mddevlabs.marketplus.data.remote.dto.CoinDto
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface CoinGeckoApi{

    @GET("coins/markets")
    suspend fun getCoinMarkets(
        @Query("vs_currency")vsCurrency:String="usd",
        @Query("per_page")perPage: Int=50,
        @Query("x_cg_demo_api_key")apiKey: String="CG-NTHuoH5VZtygyQYsGhMQM9kA"

    ): List<CoinDto>

    companion object{
        private val json= Json {
            ignoreUnknownKeys=true
            coerceInputValues=true
        }

        val instance: CoinGeckoApi  by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.coingecko.com/api/v3/")
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(CoinGeckoApi::class.java)
        }
    }
}