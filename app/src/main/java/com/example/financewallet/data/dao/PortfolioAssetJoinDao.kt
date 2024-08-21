package com.example.financewallet.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.financewallet.data.entity.AssetEntity
import com.example.financewallet.data.entity.PortfolioAssetJoin
import com.example.financewallet.data.entity.PortfolioEntity

@Dao
interface PortfolioAssetJoinDao {

    @Upsert
    suspend fun upsertPortfolioAssetJoin(join: PortfolioAssetJoin)

    @Query("SELECT * FROM assets INNER JOIN portfolio_asset_join ON assets.id = portfolio_asset_join.assetId WHERE portfolio_asset_join.portfolioId = :portfolioId")
    suspend fun getAssetsForPortfolio(portfolioId: Int): List<AssetEntity>

    @Query("SELECT * FROM portfolios INNER JOIN portfolio_asset_join ON portfolios.id = portfolio_asset_join.portfolioId WHERE portfolio_asset_join.assetId = :assetId")
    suspend fun getPortfoliosForAsset(assetId: Int): List<PortfolioEntity>

    @Query("DELETE FROM portfolio_asset_join WHERE portfolioId = :portfolioId")
    suspend fun deleteAllAssetsForPortfolio(portfolioId: Int)
}
