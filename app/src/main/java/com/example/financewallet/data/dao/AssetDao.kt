package com.example.financewallet.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.financewallet.data.entity.AssetEntity

@Dao
interface AssetDao {

    @Upsert
    suspend fun upsertAsset(asset: AssetEntity)

    @Query("SELECT * FROM assets WHERE id = :assetId")
    suspend fun getAssetById(assetId: Int): AssetEntity?

    @Query("SELECT * FROM assets")
    suspend fun getAllAssets(): List<AssetEntity>
}
