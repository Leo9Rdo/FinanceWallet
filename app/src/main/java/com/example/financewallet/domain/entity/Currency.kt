package com.example.financewallet.domain.entity

data class Currency(
    val id: Int,
    val abbreviation: String,
    val name: String,
    val exchangeRate: Double
)
