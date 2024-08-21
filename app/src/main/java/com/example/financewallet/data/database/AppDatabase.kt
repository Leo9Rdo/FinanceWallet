package com.example.financewallet.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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
import com.example.financewallet.data.dao.PortfolioAssetJoinDao
import com.example.financewallet.data.entity.CurrencyConverter
import com.example.financewallet.data.entity.DateConverter
import com.example.financewallet.data.entity.PortfolioAssetJoin

@Database(
    entities = [AssetEntity::class, CurrencyEntity::class, PortfolioEntity::class, PriceHistoryEntity::class, PortfolioAssetJoin::class],
    version = 4,
    exportSchema = true
)
@TypeConverters(DateConverter::class, CurrencyConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
    abstract fun assetDao(): AssetDao
    abstract fun currencyDao(): CurrencyDao
    abstract fun priceHistoryDao(): PriceHistoryDao
    abstract fun portfolioAssetJoinDao(): PortfolioAssetJoinDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION_3_4 = object : Migration(3, 4) {
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
                    .addMigrations(MIGRATION_3_4)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
