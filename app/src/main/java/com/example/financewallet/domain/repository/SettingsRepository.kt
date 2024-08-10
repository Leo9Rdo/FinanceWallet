package com.example.financewallet.domain.repository

interface SettingsRepository {
    fun saveCurrency(currencyName: String)
    fun getSavedCurrency(): String
}
