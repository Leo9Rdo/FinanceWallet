package com.example.financewallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financewallet.data.entity.CurrencyEntity

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM currencies WHERE id = :currencyId")
    suspend fun getCurrencyById(currencyId: Int): CurrencyEntity?

    @Query("SELECT * FROM currencies")
    suspend fun getAllCurrencies(): List<CurrencyEntity>
}
