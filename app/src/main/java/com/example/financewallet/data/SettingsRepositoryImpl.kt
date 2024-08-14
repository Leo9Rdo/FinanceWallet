package com.example.financewallet.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.financewallet.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsRepository {

    companion object {
        private val SELECTED_CURRENCY_KEY = stringPreferencesKey("selected_currency")
        private const val DEFAULT_CURRENCY = "Белорусский рубль"
    }

    override suspend fun saveCurrency(currencyName: String) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CURRENCY_KEY] = currencyName
        }
    }

    override suspend fun getSavedCurrency(): String {
        return context.dataStore.data
            .map { preferences ->
                preferences[SELECTED_CURRENCY_KEY] ?: DEFAULT_CURRENCY
            }.first()
    }
}
