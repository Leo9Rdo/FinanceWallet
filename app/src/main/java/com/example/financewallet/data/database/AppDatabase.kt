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
import com.example.financewallet.data.entity.PortfolioAssetJoin

@Database(
    entities = [AssetEntity::class, CurrencyEntity::class, PortfolioEntity::class, PriceHistoryEntity::class, PortfolioAssetJoin::class],
    version = 3,
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

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `portfolio_asset_join` (`portfolioId` INTEGER NOT NULL, `assetId` INTEGER NOT NULL, PRIMARY KEY(`portfolioId`, `assetId`), FOREIGN KEY(`portfolioId`) REFERENCES `portfolios`(`id`) ON DELETE CASCADE, FOREIGN KEY(`assetId`) REFERENCES `assets`(`id`) ON DELETE CASCADE)")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(MIGRATION_2_3)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
