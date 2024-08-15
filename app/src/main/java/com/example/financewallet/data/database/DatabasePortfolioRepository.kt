package com.example.financewallet.data.database

import com.example.financewallet.data.dao.PortfolioDao
import com.example.financewallet.data.entity.PortfolioEntity
import com.example.financewallet.domain.entity.Portfolio
import com.example.financewallet.domain.repository.PortfolioRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabasePortfolioRepository @Inject constructor(
    private val portfolioDao: PortfolioDao
) : PortfolioRepository {

    override suspend fun getPortfolio(id: Int): Portfolio = withContext(Dispatchers.IO) {
        portfolioDao.getPortfolio(id)?.toDomainModel()
            ?: throw NoSuchElementException("Portfolio not found")
    }

    override suspend fun getAllPortfolios(): List<Portfolio> = withContext(Dispatchers.IO) {
        portfolioDao.getAllPortfolios().map { it.toDomainModel() }
    }

    override suspend fun savePortfolio(portfolio: Portfolio) = withContext(Dispatchers.IO) {
        portfolioDao.savePortfolio(portfolio.toEntityModel())
    }

    override suspend fun deletePortfolio(id: Int) = withContext(Dispatchers.IO) {
        val portfolioEntity = portfolioDao.getPortfolio(id)
            ?: throw NoSuchElementException("Portfolio not found")
        portfolioDao.deletePortfolio(portfolioEntity)
    }

    private fun PortfolioEntity.toDomainModel(): Portfolio {
        return Portfolio(id = this.id, name = this.name, assets = this.assets)
    }

    private fun Portfolio.toEntityModel(): PortfolioEntity {
        return PortfolioEntity(id = this.id, name = this.name, assets = this.assets)
    }
}
