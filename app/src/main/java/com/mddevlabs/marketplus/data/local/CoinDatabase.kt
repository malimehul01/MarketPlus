package com.mddevlabs.marketplus.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [CoinEntity::class],
    version = 1,
    exportSchema = false
 )
 abstract class CoinDatabase : RoomDatabase(){
     abstract val dao: CoinDao
 }