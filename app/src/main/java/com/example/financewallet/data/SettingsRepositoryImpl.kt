package com.example.financewallet.data

import android.content.Context
import android.content.SharedPreferences
import com.example.financewallet.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : SettingsRepository {

    companion object {
        private const val PREF_NAME = "currency_preference"
        private const val SELECTED_CURRENCY = "selected_currency"
        private const val DEFAULT_CURRENCY = "Белорусский рубль"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun saveCurrency(currencyName: String) {
        sharedPreferences.edit().putString(SELECTED_CURRENCY, currencyName).apply()
    }

    override fun getSavedCurrency(): String {
        return sharedPreferences.getString(SELECTED_CURRENCY, DEFAULT_CURRENCY) ?: DEFAULT_CURRENCY
    }
}
