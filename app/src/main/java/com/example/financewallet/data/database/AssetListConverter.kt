package com.example.financewallet.data.database

import androidx.room.TypeConverter
import com.example.financewallet.domain.entity.Asset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssetListConverter {

    @TypeConverter
    fun fromAssetList(assets: List<Asset>?): String? {
        if (assets == null) {
            return null
        } else {
            val gson = Gson()
            val type = object : TypeToken<List<Asset>>() {}.type
            return gson.toJson(assets, type)
        }
    }

    @TypeConverter
    fun toAssetList(assetString: String?): List<Asset>? {
        if (assetString == null) {
            return null
        } else {
            val gson = Gson()
            val type = object : TypeToken<List<Asset>>() {}.type
            return gson.fromJson(assetString, type)
        }
    }
}
