package com.example.financewallet.domain.interactor

import com.example.financewallet.domain.entity.Currency
import com.example.financewallet.domain.repository.CurrencyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyInteractor @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {

    suspend fun getAllCurrencies(): List<Currency> {
        return currencyRepository.getAllCurrencies()
    }

    suspend fun getCurrencyByAbbreviation(abbreviation: String) {
        currencyRepository.getCurrencyByAbbreviation(abbreviation)
    }

    suspend fun getCurrency(curId: String): Currency {
        return currencyRepository.fetchCurrency(curId)
    }
}
