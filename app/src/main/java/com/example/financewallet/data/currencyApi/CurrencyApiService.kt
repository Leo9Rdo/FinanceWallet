package com.example.financewallet.data.currencyApi

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApiService {

    @GET("exrates/rates/{cur_id}")
    suspend fun getCurrency(
        @Path("cur_id") curId: String,
        @Query("ondate") ondate: String? = null,
        @Query("periodicity") periodicity: Int = 0,
        @Query("parammode") parammode: Int = 2
    ): CurrencyResponse
}
