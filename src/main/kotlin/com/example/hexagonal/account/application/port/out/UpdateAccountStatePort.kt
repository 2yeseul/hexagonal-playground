package com.example.hexagonal.account.application.port.out

import com.example.hexagonal.account.domain.Account

interface UpdateAccountStatePort {
    fun updateActivities(account: Account)
}
