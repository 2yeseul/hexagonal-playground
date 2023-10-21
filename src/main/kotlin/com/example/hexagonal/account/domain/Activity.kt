package com.example.hexagonal.account.domain

import java.time.LocalDateTime

data class Activity(
    var id: Long? = null,
    val ownerAccountId: Long,
    val sourceAccountId: Long,
    val targetAccountId: Long,
    val timestamp: LocalDateTime,
    val money: Money,
)
