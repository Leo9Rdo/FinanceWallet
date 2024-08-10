package com.example.financewallet.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financewallet.domain.interactor.CurrencyInteractor
import com.example.financewallet.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val currencyInteractor: CurrencyInteractor
) : ViewModel() {

    private val _selectedCurrencyModel = MutableLiveData<String?>()
    val selectedCurrencyModel: LiveData<String?> get() = _selectedCurrencyModel

    private val _availableCurrencies = MutableLiveData<List<String>>()
    val availableCurrencies: LiveData<List<String>> get() = _availableCurrencies

    init {
        getAvailableCurrenciesNames()
    }

    fun selectCurrency(currencyName: String) {
        settingsRepository.saveCurrency(currencyName)
        _selectedCurrencyModel.value = settingsRepository.getSavedCurrency()
    }

    private fun getAvailableCurrenciesNames() {
        viewModelScope.launch {
            _availableCurrencies.value = currencyInteractor.getAllCurrencies().map { it.name }
        }
    }
}
