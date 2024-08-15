package com.example.financewallet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financewallet.data.entity.PortfolioEntity

@Dao
interface PortfolioDao {
    @Query("SELECT * FROM portfolios WHERE id=:id")
    suspend fun getPortfolio(id: Int): PortfolioEntity?

    @Query("SELECT * FROM portfolios")
    suspend fun getAllPortfolios(): List<PortfolioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePortfolio(portfolio: PortfolioEntity)

    @Delete
    suspend fun deletePortfolio(portfolio: PortfolioEntity)
}
