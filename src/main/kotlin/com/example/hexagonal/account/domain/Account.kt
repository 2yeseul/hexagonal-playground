package com.example.hexagonal.account.domain

data class Account(
    val id: AccountId,
    val baselineBalance: Money,
)

data class AccountId(
    val id: Long,
)
