package com.example.hexagonal.account.application.port.`in`

import com.example.hexagonal.account.domain.Money

interface SendMoneyUseCase {
    fun sendMoney(command: SendMoneyCommand): Boolean
}

data class SendMoneyCommand(
    val sourceAccountId: Long,
    val targetAccountId: Long,
    val money: Money,
)
