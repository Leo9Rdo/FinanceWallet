package com.example.financewallet.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.financewallet.domain.entity.Asset

@Entity(
    tableName = "portfolios"
)

data class PortfolioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val assets: List<Asset>
)
