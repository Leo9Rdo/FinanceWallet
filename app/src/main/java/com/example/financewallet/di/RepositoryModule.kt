package com.example.financewallet.di

import com.example.financewallet.data.AssetRepositoryImpl
import com.example.financewallet.data.PortfolioRepositoryImpl
import com.example.financewallet.data.database.DatabasePortfolioRepository
import com.example.financewallet.domain.repository.AssetRepository
import com.example.financewallet.domain.repository.PortfolioRepository
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
    fun bindAssetListRepository(assetRepositoryImpl: AssetRepositoryImpl): AssetRepository

    @Binds
    @Singleton
    @Named("InMemory")
    fun bindInMemoryPortfolioRepository(
        portfolioRepositoryImpl: PortfolioRepositoryImpl
    ): PortfolioRepository

    @Binds
    @Singleton
    @Named("Database")
    fun bindDatabasePortfolioRepository(
        databasePortfolioRepository: DatabasePortfolioRepository
    ): PortfolioRepository
}
