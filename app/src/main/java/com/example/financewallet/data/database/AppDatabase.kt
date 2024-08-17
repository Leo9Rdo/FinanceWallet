package com.example.financewallet.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.financewallet.data.dao.AssetDao
import com.example.financewallet.data.dao.CurrencyDao
import com.example.financewallet.data.dao.PortfolioDao
import com.example.financewallet.data.dao.PriceHistoryDao
import com.example.financewallet.data.entity.AssetEntity
import com.example.financewallet.data.entity.CurrencyEntity
import com.example.financewallet.data.entity.PortfolioEntity
import com.example.financewallet.data.entity.PriceHistoryEntity
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [AssetEntity::class, CurrencyEntity::class, PortfolioEntity::class, PriceHistoryEntity::class],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
    abstract fun assetDao(): AssetDao
    abstract fun currencyDao(): CurrencyDao
    abstract fun priceHistoryDao(): PriceHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE portfolios ADD COLUMN new_column INTEGER DEFAULT 0")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
