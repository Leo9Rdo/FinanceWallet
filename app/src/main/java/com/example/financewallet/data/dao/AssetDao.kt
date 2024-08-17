package com.example.financewallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financewallet.data.entity.AssetEntity

@Dao
interface AssetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsset(asset: AssetEntity)

    @Query("SELECT * FROM assets WHERE id = :assetId")
    suspend fun getAssetById(assetId: Int): AssetEntity?

    @Query("SELECT * FROM assets")
    suspend fun getAllAssets(): List<AssetEntity>
}
