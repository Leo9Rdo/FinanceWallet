package com.example.financewallet.data.entity

import androidx.room.TypeConverter
import com.example.financewallet.domain.entity.Currency

class CurrencyConverter {
    @TypeConverter
    fun fromCurrency(currency: Currency): String {
        return currency.abbreviation
    }
    @TypeConverter
    fun toCurrency(currencyString: String): Currency {
        return Currency(id = 0, abbreviation = currencyString, name = "", exchangeRate = 0.0)
    }
}
