package com.example.financewallet.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey val id: Int,
    val abbreviation: String,
    val name: String,
    val exchangeRate: Double
)
