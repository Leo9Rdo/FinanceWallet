package com.example.financewallet.data.currencyApi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(
    @SerialName("Cur_ID") val curId: Int,
    @SerialName("Date") val date: String,
    @SerialName("Cur_Abbreviation") val curAbbreviation: String,
    @SerialName("Cur_Name") val curName: String,
    @SerialName("Cur_OfficialRate") val curOfficialRate: Double,
    @SerialName("Cur_Scale") val curScale: Int
)
