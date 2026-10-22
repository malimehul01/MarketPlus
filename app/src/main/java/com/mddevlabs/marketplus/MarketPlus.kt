package com.mddevlabs.marketplus

import android.app.Application
import androidx.room.Room
import com.mddevlabs.marketplus.data.local.CoinDatabase
import com.mddevlabs.marketplus.data.remote.CoinGeckoApi
import com.mddevlabs.marketplus.data.repository.CoinRepository
import com.mddevlabs.marketplus.data.repository.CoinRepositoryImpl

class MarketPlus: Application(){
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            CoinDatabase::class.java,
            "marketplus.db"
        ).build()
    }

    val repository: CoinRepository by lazy {
        CoinRepositoryImpl(
            api= CoinGeckoApi.instance,
            dao = database.dao
        )
    }
}