package com.example.hexagonal.account.application.service

import com.example.hexagonal.account.application.port.`in`.SendMoneyCommand
import com.example.hexagonal.account.application.port.`in`.SendMoneyUseCase
import com.example.hexagonal.account.application.port.out.LoadAccountPort
import com.example.hexagonal.account.application.port.out.UpdateAccountStatePort
import com.example.hexagonal.account.domain.Money
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.time.LocalDateTime

@Service
class SendMoneyService(
    private val loadAccountPort: LoadAccountPort,
    private val updateAccountStatePort: UpdateAccountStatePort,
) : SendMoneyUseCase {
    override fun sendMoney(command: SendMoneyCommand): Boolean {
        TODO("Not yet implemented")

        checkThreshold(command)

        val baselineDate = LocalDateTime.now().minusDays(10)
        val sourceAccount = loadAccountPort.loadAccount(command.sourceAccountId, baselineDate)
        val targetAccount = loadAccountPort.loadAccount(command.targetAccountId, baselineDate)

        // some lock logic

        updateAccountStatePort.updateActivities(sourceAccount)
        updateAccountStatePort.updateActivities(targetAccount)

        // some release logic

        return true
    }

    private fun checkThreshold(command: SendMoneyCommand): Unit =
        if (command.money.isGreaterThan(Money.of(BigInteger.valueOf(1_000_000L)))) {
            throw IllegalStateException()
        } else {
            Unit
        }
}
