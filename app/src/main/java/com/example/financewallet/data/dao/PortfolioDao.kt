package com.example.financewallet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.financewallet.data.entity.PortfolioEntity
import com.example.financewallet.data.entity.PortfolioWithAssets

@Dao
interface PortfolioDao {

    @Query("SELECT * FROM portfolios WHERE id = :id")
    suspend fun getPortfolio(id: Int): PortfolioEntity?

    @Query("SELECT * FROM portfolios")
    suspend fun getAllPortfolios(): List<PortfolioEntity>

    @Upsert
    suspend fun upsertPortfolio(portfolio: PortfolioEntity)

    @Delete
    suspend fun deletePortfolio(portfolio: PortfolioEntity)

    @Transaction
    @Query("SELECT * FROM portfolios WHERE id = :portfolioId")
    suspend fun getPortfolioWithAssets(portfolioId: Int): PortfolioWithAssets?
}
