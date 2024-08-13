package com.example.financewallet.domain.repository

import com.example.financewallet.domain.entity.Portfolio

interface PortfolioRepository {
    suspend fun getPortfolio(id: Int): Portfolio
    suspend fun getAllPortfolios(): List<Portfolio>
    suspend fun savePortfolio(portfolio: Portfolio)
    suspend fun deletePortfolio(id: Int)
}
