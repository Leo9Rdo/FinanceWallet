package com.example.financewallet.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.financewallet.data.entity.CurrencyEntity

@Dao
interface CurrencyDao {

    @Upsert
    suspend fun upsertCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM currencies WHERE id = :currencyId")
    suspend fun getCurrencyById(currencyId: Int): CurrencyEntity?

    @Query("SELECT * FROM currencies")
    suspend fun getAllCurrencies(): List<CurrencyEntity>
}
