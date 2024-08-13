package com.example.financewallet.data

import com.example.financewallet.domain.entity.Portfolio
import com.example.financewallet.domain.repository.PortfolioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PortfolioRepositoryImpl @Inject constructor() : PortfolioRepository {
    private val portfolios = mutableMapOf<Int, Portfolio>()

    override suspend fun getPortfolio(id: Int): Portfolio = withContext(Dispatchers.IO) {
        portfolios[id] ?: throw NoSuchElementException("Portfolio not found")
    }

    override suspend fun getAllPortfolios(): List<Portfolio> = withContext(Dispatchers.IO) {
        portfolios.values.toList()
    }

    override suspend fun savePortfolio(portfolio: Portfolio) = withContext(Dispatchers.IO) {
        val newId = (portfolios.keys.maxOrNull() ?: 0) + 1
        if (portfolio.id == 0 || !portfolios.containsKey(portfolio.id)) {
            val newPortfolio = portfolio.copy(id = newId)
            portfolios[newPortfolio.id] = newPortfolio
        } else {
            portfolios[portfolio.id] = portfolio
        }
    }

    override suspend fun deletePortfolio(id: Int): Unit = withContext(Dispatchers.IO)  {
        portfolios.remove(id)
    }
}
