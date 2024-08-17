package com.example.financewallet.domain.interactor

import com.example.financewallet.domain.repository.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsInteractor @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    suspend fun saveCurrency(currencyName: String) {
        settingsRepository.saveCurrency(currencyName)
    }

    suspend fun getSavedCurrency(): String {
        return settingsRepository.getSavedCurrency()
    }
}
