package com.example.hexagonal.account.domain

data class Account(
    val id: AccountId,
)

data class AccountId(
    val id: Long,
)
