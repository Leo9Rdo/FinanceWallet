package com.example.financewallet.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "assets",
    foreignKeys = [ForeignKey(
        entity = PortfolioEntity::class,
        parentColumns = ["id"],
        childColumns = ["portfolioId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["portfolioId"])]
)

data class AssetEntity(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val currency: String,
    val marketValue: Double,
    val purchaseDate: String,
    val type: String,
    val portfolioId: Int,
    val amount: Int,
    val ticker: String,
    val couponRate: Double,
    val expiryDate: String,
    val price: Double,
    val exchangeRate: Double
)
