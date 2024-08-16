package com.example.financewallet.domain.repository

import com.example.financewallet.domain.entity.Asset

interface AssetRepository {
    suspend fun getAllAssets(): List<Asset>
}
