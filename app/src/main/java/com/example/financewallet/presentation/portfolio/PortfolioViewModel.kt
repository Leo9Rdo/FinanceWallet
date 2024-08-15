package com.example.financewallet.presentation.portfolio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financewallet.domain.entity.Portfolio
import com.example.financewallet.domain.interactor.PortfolioInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val portfolioInteractor: PortfolioInteractor
) : ViewModel() {

    private val _portfolios = MutableLiveData<List<Portfolio>>()
    val portfolios: LiveData<List<Portfolio>> get() = _portfolios

    init {
        loadPortfolios()
    }

    fun loadPortfolios() {
        viewModelScope.launch {
            _portfolios.value = portfolioInteractor.getAllPortfolios()
        }
    }

    fun savePortfolio(portfolio: Portfolio) {
        viewModelScope.launch {
            portfolioInteractor.savePortfolio(portfolio)
            loadPortfolios()
        }
    }

    fun deletePortfolio(id: Int) {
        viewModelScope.launch {
            portfolioInteractor.deletePortfolio(id)
            _portfolios.value = _portfolios.value?.filter { it.id != id }
        }
    }
}
