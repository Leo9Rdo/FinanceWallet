package com.example.financewallet.domain.repository

interface SettingsRepository {
    suspend fun saveCurrency(currencyName: String)
    suspend fun getSavedCurrency(): String
}
