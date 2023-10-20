package com.example.hexagonal.account.domain

import java.time.LocalDateTime

data class Activity(
    val id: ActivityId,
    val ownerAccountId: AccountId,
    val sourceAccountId: AccountId,
    val targetAccountId: AccountId,
    val timestamp: LocalDateTime,
    val money: Money
)

data class ActivityId(
    val id: Long,
)
