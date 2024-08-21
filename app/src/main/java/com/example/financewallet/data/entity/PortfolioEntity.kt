package com.example.financewallet.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "portfolios"
)
data class PortfolioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
