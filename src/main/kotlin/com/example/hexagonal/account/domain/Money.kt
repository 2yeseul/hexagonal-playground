package com.example.hexagonal.account.domain

import java.math.BigInteger

data class Money(
    val amount: BigInteger,
) {
    fun isPositiveOrZero(): Boolean =
        this.amount.compareTo(BigInteger.ZERO) >= 0

    fun isNegative(): Boolean =
        this.amount.compareTo(BigInteger.ZERO) < 0

    fun isPositive(): Boolean =
        this.amount.compareTo(BigInteger.ZERO) > 0

    fun isGreaterThanOrEqualTo(other: Money): Boolean =
        this.amount.compareTo(other.amount) >= 0

    fun isGreaterThan(other: Money): Boolean =
        this.amount.compareTo(other.amount) > 0

    fun minus(other: Money): Money =
        Money.of(this.amount.subtract(other.amount))

    fun plus(other: Money): Money =
        Money.of(this.amount.add(other.amount))

    fun negate(): Money =
        Money.of(this.amount.negate())

    companion object {
        val ZERO = Money.of(BigInteger.ZERO)

        fun of(amount: BigInteger): Money = Money(amount)

        fun add(money1: Money, money2: Money): Money =
            Money.of(money1.amount.add(money2.amount))

        fun subtract(money1: Money, money2: Money): Money =
            Money.of(money1.amount.subtract(money2.amount))
    }
}
