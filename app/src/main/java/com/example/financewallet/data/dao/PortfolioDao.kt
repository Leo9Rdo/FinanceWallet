package com.example.financewallet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.financewallet.data.entity.PortfolioEntity

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

}
