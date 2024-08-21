package com.example.financewallet.data.database

import com.example.financewallet.data.dao.PortfolioAssetJoinDao
import com.example.financewallet.data.dao.PortfolioDao
import com.example.financewallet.data.entity.AssetEntity
import com.example.financewallet.data.entity.PortfolioAssetJoin
import com.example.financewallet.data.entity.PortfolioEntity
import com.example.financewallet.domain.entity.Asset
import com.example.financewallet.domain.entity.Bond
import com.example.financewallet.domain.entity.Cash
import com.example.financewallet.domain.entity.Currency
import com.example.financewallet.domain.entity.Portfolio
import com.example.financewallet.domain.entity.Stock
import com.example.financewallet.domain.repository.PortfolioRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class DatabasePortfolioRepository @Inject constructor(
    private val portfolioDao: PortfolioDao,
    private val portfolioAssetJoinDao: PortfolioAssetJoinDao
) : PortfolioRepository {

    override suspend fun getPortfolio(id: Int): Portfolio = withContext(Dispatchers.IO) {
        val portfolioEntity = portfolioDao.getPortfolio(id)
            ?: throw NoSuchElementException("Portfolio not found")

        val assets = portfolioAssetJoinDao.getAssetsForPortfolio(id)

        portfolioEntity.toDomainModel(assets)
    }

    override suspend fun getAllPortfolios(): List<Portfolio> = withContext(Dispatchers.IO) {
        portfolioDao.getAllPortfolios().map { portfolioEntity ->
            val assets = portfolioAssetJoinDao.getAssetsForPortfolio(portfolioEntity.id)
            portfolioEntity.toDomainModel(assets)
        }
    }

    override suspend fun savePortfolio(portfolio: Portfolio) = withContext(Dispatchers.IO) {
        val portfolioEntity = portfolio.toEntityModel()
        portfolioDao.upsertPortfolio(portfolioEntity)
        portfolioAssetJoinDao.deleteAllAssetsForPortfolio(portfolioEntity.id)
        portfolio.assets.forEach { asset ->
            portfolioAssetJoinDao.upsertPortfolioAssetJoin(
                PortfolioAssetJoin(portfolioEntity.id, asset.id)
            )
        }
    }

    override suspend fun deletePortfolio(id: Int) = withContext(Dispatchers.IO) {
        val portfolioEntity = portfolioDao.getPortfolio(id)
            ?: throw NoSuchElementException("Portfolio not found")
        portfolioDao.deletePortfolio(portfolioEntity)
    }

    private fun PortfolioEntity.toDomainModel(assets: List<AssetEntity>): Portfolio {
        return Portfolio(id = this.id, name = this.name, assets = assets.map { it.toDomainModel() })
    }

    private fun Portfolio.toEntityModel(): PortfolioEntity {
        return PortfolioEntity(id = this.id, name = this.name)
    }

    private fun AssetEntity.toDomainModel(): Asset {
        return when (this.type) {
            "Cash" -> Cash(
                id = this.id,
                name = this.name,
                currency = Currency(
                    id = 0,
                    abbreviation = this.currency,
                    name = "",
                    exchangeRate = 0.0
                ),
                marketValue = this.marketValue,
                purchaseDate = LocalDate.parse(this.purchaseDate)
            )

            "Stock" -> Stock(
                id = this.id,
                name = this.name,
                currency = Currency(
                    id = 0,
                    abbreviation = this.currency,
                    name = "",
                    exchangeRate = 0.0
                ),
                marketValue = this.marketValue,
                purchaseDate = LocalDate.parse(this.purchaseDate),
                amount = this.amount,
                ticker = this.ticker
            )

            "Bond" -> Bond(
                id = this.id,
                name = this.name,
                currency = Currency(
                    id = 0,
                    abbreviation = this.currency,
                    name = "",
                    exchangeRate = 0.0
                ),
                marketValue = this.marketValue,
                purchaseDate = LocalDate.parse(this.purchaseDate),
                couponRate = this.couponRate,
                expiryDate = LocalDate.parse(
                    this.expiryDate
                ),
                amount = this.amount,
                price = this.price
            )

            else -> throw IllegalArgumentException("Unknown asset type: ${this.type}")
        }
    }
}
