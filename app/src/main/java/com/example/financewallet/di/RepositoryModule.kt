package com.example.financewallet.di

import com.example.financewallet.data.AssetRepositoryImpl
import com.example.financewallet.data.CurrencyRepositoryImpl
import com.example.financewallet.data.PortfolioRepositoryImpl
import com.example.financewallet.data.SettingsRepositoryImpl
import com.example.financewallet.data.database.DatabasePortfolioRepository
import com.example.financewallet.domain.repository.AssetRepository
import com.example.financewallet.domain.repository.CurrencyRepository
import com.example.financewallet.domain.repository.PortfolioRepository
import com.example.financewallet.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAssetRepository(assetRepositoryImpl: AssetRepositoryImpl): AssetRepository

    @Binds
    @Singleton
    fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    @Singleton
    @Named(QUALIFIER_IN_MEMORY)
    fun bindInMemoryPortfolioRepository(
        portfolioRepositoryImpl: PortfolioRepositoryImpl
    ): PortfolioRepository

    @Binds
    @Singleton
    @Named(QUALIFIER_DATABASE)
    fun bindDatabasePortfolioRepository(
        databasePortfolioRepository: DatabasePortfolioRepository
    ): PortfolioRepository

    @Binds
    @Singleton
    fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository

    companion object {
        const val QUALIFIER_IN_MEMORY = "InMemory"
        const val QUALIFIER_DATABASE = "Database"
    }
}
