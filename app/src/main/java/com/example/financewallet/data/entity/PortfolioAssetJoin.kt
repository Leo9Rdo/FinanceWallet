package com.example.financewallet.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

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
    ],
    indices = [Index(value = ["portfolioId"]), Index(value = ["assetId"])]
)
data class PortfolioAssetJoin(
    val portfolioId: Int,
    val assetId: Int
)
