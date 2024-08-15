package com.example.financewallet.di

import android.content.Context
import androidx.room.Room
import com.example.financewallet.data.dao.PortfolioDao
import com.example.financewallet.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "finance_portfolio_db"
        ).build()
    }

    @Provides
    fun providePortfolioDao(database: AppDatabase): PortfolioDao {
        return database.portfolioDao()
    }
}
