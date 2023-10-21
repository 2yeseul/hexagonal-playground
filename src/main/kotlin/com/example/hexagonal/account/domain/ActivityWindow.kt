package com.example.hexagonal.account.domain

import java.time.LocalDateTime

data class ActivityWindow(
    val activities: List<Activity>,
) {
    fun getStartTimestamp(): LocalDateTime =
        activities.first().timestamp

    fun getEndTimestamp(): LocalDateTime =
        activities.last().timestamp

    fun calculateBalance(accountId: Long): Money =
        Money.add(
            activities
                .filter { it.targetAccountId.equals(accountId) }
                .map { it.money }
                .reduce(Money::add),
            activities
                .filter { it.sourceAccountId.equals(accountId) }
                .map { it.money }
                .reduce(Money::add),
        )

    fun addActivity(activity: Activity) =
        this.activities + activity
}
