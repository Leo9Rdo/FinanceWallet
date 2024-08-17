package com.example.financewallet.domain.entity

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
abstract class Asset(
    open val id: Int,
    open val name: String,
  @Contextual  open val currency: Currency,
    open val marketValue: Double,
    @Contextual open val purchaseDate: LocalDate
) {
    abstract fun areContentsTheSame(other: Asset): Boolean
}

data class Cash(
    override val id: Int,
    override val name: String,
    override val currency: Currency,
    override val marketValue: Double,
    override val purchaseDate: LocalDate
) : Asset(id, name, currency, marketValue, purchaseDate) {
    override fun areContentsTheSame(other: Asset): Boolean {
        return id == other.id &&
                name == other.name &&
                currency == other.currency &&
                marketValue == other.marketValue &&
                purchaseDate == other.purchaseDate
    }
}

data class Stock(
    override val id: Int,
    override val name: String,
    override val currency: Currency,
    override val marketValue: Double,
    override val purchaseDate: LocalDate,
    val amount: Int,
    val ticker: String
) : Asset(id, name, currency, marketValue, purchaseDate) {
    override fun areContentsTheSame(other: Asset): Boolean {
        return if (other is Stock) {
            id == other.id &&
                    name == other.name &&
                    currency == other.currency &&
                    marketValue == other.marketValue &&
                    purchaseDate == other.purchaseDate &&
                    amount == other.amount &&
                    ticker == other.ticker
        } else false
    }
}

data class Bond(
    override val id: Int,
    override val name: String,
    override val currency: Currency,
    override val marketValue: Double,
    override val purchaseDate: LocalDate,
    val couponRate: Double,
    val expiryDate: LocalDate,
    val amount: Int,
    val price: Double
) : Asset(id, name, currency, marketValue, purchaseDate) {
    override fun areContentsTheSame(other: Asset): Boolean {
        return if (other is Bond) {
            id == other.id &&
                    name == other.name &&
                    currency == other.currency &&
                    marketValue == other.marketValue &&
                    purchaseDate == other.purchaseDate &&
                    couponRate == other.couponRate &&
                    expiryDate == other.expiryDate &&
                    amount == other.amount &&
                    price == other.price
        } else false
    }
}
