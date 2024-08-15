package com.example.financewallet.domain.interactor

import com.example.financewallet.domain.entity.Portfolio
import com.example.financewallet.domain.repository.PortfolioRepository
import javax.inject.Inject
import javax.inject.Named

class PortfolioInteractor @Inject constructor(
    @Named("Database") private val portfolioRepository: PortfolioRepository
) {
    suspend fun getPortfolio(id: Int): Portfolio {
        return portfolioRepository.getPortfolio(id)
    }

    suspend fun getAllPortfolios(): List<Portfolio> {
        return portfolioRepository.getAllPortfolios()
    }

    suspend fun savePortfolio(portfolio: Portfolio) {
        portfolioRepository.savePortfolio(portfolio)
    }

    suspend fun deletePortfolio(id: Int) {
        portfolioRepository.deletePortfolio(id)
    }
}
