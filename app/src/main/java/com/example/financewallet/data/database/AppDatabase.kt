package com.example.financewallet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.financewallet.data.dao.PortfolioDao
import com.example.financewallet.data.entity.PortfolioEntity

@Database(
    entities = [PortfolioEntity::class],
    version = 1
)

@TypeConverters(AssetListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun portfolioDao(): PortfolioDao
}
