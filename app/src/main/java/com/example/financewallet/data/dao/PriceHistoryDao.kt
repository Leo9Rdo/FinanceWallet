package com.example.financewallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financewallet.data.entity.PriceHistoryEntity

@Dao
interface PriceHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceHistory(priceHistory: PriceHistoryEntity)

    @Query("SELECT * FROM price_history WHERE assetId = :assetId")
    suspend fun getPriceHistoryByAssetId(assetId: Int): List<PriceHistoryEntity>

    @Query("SELECT * FROM price_history")
    suspend fun getAllPriceHistories(): List<PriceHistoryEntity>
}
