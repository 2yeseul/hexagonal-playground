package com.example.hexagonal.account.adapter.out

import com.example.hexagonal.account.domain.Activity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ActivityRepository : JpaRepository<Activity, Long> {
    // findByOwnerSince
    fun findByOwnerAccountIdAndTimestampGreaterThanEqual(
        accountId: Long,
        timestamp: LocalDateTime,
    ): List<Activity>

    // getDepositBalanceUntil
    fun findByTargetAccountIdAndOwnerAccountIdAndTimestampBefore(
        targetAccountId: Long,
        ownerAccountId: Long,
        timestamp: LocalDateTime,
    ): List<Activity>

    // getWithdrawBalanceUntil
    fun findBySourceAccountIdAndOwnerAccountIdAndTimestampBefore(
        sourceAccountId: Long,
        ownerAccountId: Long,
        timestamp: LocalDateTime,
    ): List<Activity>
}
