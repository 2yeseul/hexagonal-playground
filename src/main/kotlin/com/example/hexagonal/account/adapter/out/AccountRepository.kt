package com.example.hexagonal.account.adapter.out

import com.example.hexagonal.account.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long>
