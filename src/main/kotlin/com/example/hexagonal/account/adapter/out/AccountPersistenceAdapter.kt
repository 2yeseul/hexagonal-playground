package com.example.hexagonal.account.adapter.out

import com.example.hexagonal.account.application.port.out.LoadAccountPort
import com.example.hexagonal.account.application.port.out.UpdateAccountStatePort
import com.example.hexagonal.account.domain.Account
import com.example.hexagonal.account.domain.ActivityWindow
import com.example.hexagonal.account.domain.Money
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AccountPersistenceAdapter(
    private val accountRepository: AccountRepository,
    private val activityRepository: ActivityRepository,
) : LoadAccountPort, UpdateAccountStatePort {
    override fun loadAccount(accountId: Long, baselineDate: LocalDateTime): Account {
        TODO("Not yet implemented")

        val account = accountRepository.findById(accountId)
            .orElseThrow { throw IllegalStateException() }

        val activities = activityRepository.findByOwnerAccountIdAndTimestampGreaterThanEqual(accountId, baselineDate)
        val withdrawalBalance = activityRepository.findByTargetAccountIdAndOwnerAccountIdAndTimestampBefore(
            accountId,
            accountId,
            baselineDate,
        ).size
        val depositBalance = activityRepository.findBySourceAccountIdAndOwnerAccountIdAndTimestampBefore(
            accountId,
            accountId,
            baselineDate,
        ).size

        return Account.withId(
            id = account.id!!,
            baselineBalance = Money.subtract(
                Money.of(depositBalance.toBigInteger()),
                Money.of(withdrawalBalance.toBigInteger()),
            ),
            activityWindow = ActivityWindow(activities),
        )
    }

    override fun updateActivities(account: Account) {
        account.activityWindow.activities.filter { it.id == null }
            .forEach {
                activityRepository.save(it)
            }
    }
}
