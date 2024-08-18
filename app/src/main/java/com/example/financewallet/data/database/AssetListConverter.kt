package com.example.financewallet.data.database

import androidx.room.TypeConverter
import com.example.financewallet.domain.entity.Asset
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AssetListConverter {

    @TypeConverter
    fun fromAssetList(assets: List<Asset>?): String? {
        return assets?.let { Json.encodeToString(it) }
    }

    @TypeConverter
    fun toAssetList(assetString: String?): List<Asset>? {
        return assetString?.let { Json.decodeFromString(it) }
    }
}
