package com.example.financewallet.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_history")
data class PriceHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val assetId: Int,
    val date: String,
    val price: Double
)
