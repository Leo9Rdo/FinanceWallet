package com.example.financewallet.presentation.assetList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financewallet.domain.entity.Asset
import com.example.financewallet.domain.repository.AssetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AssetListViewModel @Inject constructor(
    private val assetRepository: AssetRepository
) : ViewModel() {

    private val _assetsModel = MutableLiveData<List<Asset>>()
    val assetsModel: LiveData<List<Asset>> get() = _assetsModel

    fun loadAllAssets() {
        viewModelScope.launch {
            _assetsModel.value = assetRepository.getAllAssets()
        }
    }
}
