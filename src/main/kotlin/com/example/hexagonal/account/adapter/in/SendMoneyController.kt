package com.example.hexagonal.account.adapter.`in`

import com.example.hexagonal.account.application.port.`in`.SendMoneyCommand
import com.example.hexagonal.account.application.port.`in`.SendMoneyUseCase
import com.example.hexagonal.account.domain.Money
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class SendMoneyController(
    private val sendMoneyUseCase: SendMoneyUseCase,
) {

    @PostMapping("/send/{sourceAccountId}/{targetAccountId}/{amount}")
    fun sendMoney(
        sourceAccountId: Long,
        targetAccountId: Long,
        amount: Long,
    ) {
        sendMoneyUseCase.sendMoney(
            SendMoneyCommand(
                sourceAccountId = sourceAccountId,
                targetAccountId = targetAccountId,
                money = Money.of(amount.toBigInteger()),
            ),
        )
    }
}
