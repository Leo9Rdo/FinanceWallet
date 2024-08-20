package com.example.financewallet.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "portfolio_asset_join",
    primaryKeys = ["portfolioId", "assetId"],
    foreignKeys = [
        ForeignKey(
            entity = PortfolioEntity::class,
            parentColumns = ["id"],
            childColumns = ["portfolioId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AssetEntity::class,
            parentColumns = ["id"],
            childColumns = ["assetId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PortfolioAssetJoin(
    val portfolioId: Int,
    val assetId: Int
)
