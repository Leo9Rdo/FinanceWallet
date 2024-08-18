package com.example.financewallet.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.financewallet.data.entity.PriceHistoryEntity

@Dao
interface PriceHistoryDao {

    @Upsert
    suspend fun upsertPriceHistory(priceHistory: PriceHistoryEntity)

    @Query("SELECT * FROM price_history WHERE assetId = :assetId")
    suspend fun getPriceHistoryByAssetId(assetId: Int): List<PriceHistoryEntity>

    @Query("SELECT * FROM price_history")
    suspend fun getAllPriceHistories(): List<PriceHistoryEntity>
}
