package com.example.hexagonal.account.application.port.out

import com.example.hexagonal.account.domain.Account
import java.time.LocalDateTime

interface LoadAccountPort {
    fun loadAccount(accountId: Long, baselineDate: LocalDateTime): Account
}
