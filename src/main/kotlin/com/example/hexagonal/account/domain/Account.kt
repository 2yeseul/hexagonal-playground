package com.example.hexagonal.account.domain

import java.time.LocalDateTime

data class Account(
    var id: Long? = null,
    var baselineBalance: Money,
    var activityWindow: ActivityWindow,
) {

    fun calculateBalance(): Money =
        this.id?.let {
            Money.add(
                this.baselineBalance,
                this.activityWindow.calculateBalance(it),
            )
        } ?: throw IllegalStateException("Account not persisted yet")

    fun withdraw(money: Money, targetAccountId: Long): Boolean =
        if (mayWithdraw(money)) {
            this.activityWindow.addActivity(
                Activity(
                    ownerAccountId = this.id!!,
                    sourceAccountId = this.id!!,
                    targetAccountId = targetAccountId,
                    money = money,
                    timestamp = LocalDateTime.now(),
                ),
            )
            true
        } else {
            false
        }

    fun deposit(money: Money, sourceAccountId: Long): Boolean =
        this.activityWindow.addActivity(
            Activity(
                ownerAccountId = this.id!!,
                sourceAccountId = sourceAccountId,
                targetAccountId = this.id!!,
                money = money,
                timestamp = LocalDateTime.now(),
            ),
        ).run { true }

    private fun mayWithdraw(money: Money): Boolean =
        Money.add(
            this.calculateBalance(),
            money.negate(),
        ).isPositiveOrZero()

    companion object {
        fun withoutId(
            baselineBalance: Money,
            activityWindow: ActivityWindow,
        ): Account =
            Account(
                baselineBalance = baselineBalance,
                activityWindow = activityWindow,
            )

        fun withId(
            id: Long,
            baselineBalance: Money,
            activityWindow: ActivityWindow,
        ): Account =
            Account(
                id = id,
                baselineBalance = baselineBalance,
                activityWindow = activityWindow,
            )
    }
}
