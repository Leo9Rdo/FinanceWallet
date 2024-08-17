package com.example.financewallet.data

import com.example.financewallet.data.currencyApi.CurrencyApiService
import com.example.financewallet.data.currencyApi.CurrencyResponse
import com.example.financewallet.domain.entity.Currency
import com.example.financewallet.domain.repository.CurrencyRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyApiService
) : CurrencyRepository {

    override suspend fun getAllCurrencies(): List<Currency> {
        return withContext(Dispatchers.IO) {
            val currencyList = listOf(
                Currency(1, "BYN", "Белорусский рубль", 1.0),
                fetchCurrency("USD"),
                fetchCurrency("EUR")
            )
            currencyList
        }
    }

    override suspend fun getCurrencyByAbbreviation(abbreviation: String): Currency {
        return withContext(Dispatchers.IO) {
            getAllCurrencies().find { it.abbreviation == abbreviation }
                ?: throw NoSuchElementException("No such currency")
        }
    }

    override suspend fun fetchCurrency(curId: String): Currency {
        return withContext(Dispatchers.IO) {
            val response = api.getCurrency(curId)
            mapToCurrency(response)
        }
    }

    private fun mapToCurrency(apiCurrency: CurrencyResponse): Currency {
        return Currency(
            id = apiCurrency.curId,
            abbreviation = apiCurrency.curAbbreviation,
            name = apiCurrency.curName,
            exchangeRate = apiCurrency.curOfficialRate / apiCurrency.curScale
        )
    }
}
