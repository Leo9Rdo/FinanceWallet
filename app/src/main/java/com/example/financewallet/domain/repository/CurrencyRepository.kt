package com.example.financewallet.domain.repository

import com.example.financewallet.domain.entity.Currency

interface CurrencyRepository {
    suspend fun getAllCurrencies(): List<Currency>
    suspend fun getCurrencyByAbbreviation(abbreviation: String): Currency
    suspend fun fetchCurrency(curId: String): Currency
}
