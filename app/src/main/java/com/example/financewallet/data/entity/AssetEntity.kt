package com.example.financewallet.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.financewallet.data.database.AssetListConverter

@Entity(
    tableName = "assets",
    foreignKeys = [ForeignKey(
        entity = PortfolioEntity::class,
        parentColumns = ["id"],
        childColumns = ["portfolioId"],
        onDelete = ForeignKey.CASCADE
    )]
)
@TypeConverters(AssetListConverter::class)
data class AssetEntity(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val currencyId: Int,
    val marketValue: Double,
    val purchaseDate: String,
    val type: String,
    val portfolioId: Int
)
