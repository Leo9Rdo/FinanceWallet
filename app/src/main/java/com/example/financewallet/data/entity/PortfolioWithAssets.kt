package com.example.financewallet.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PortfolioWithAssets(
    @Embedded val portfolio: PortfolioEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "portfolioId"
    )
    val assets: List<AssetEntity>
)
