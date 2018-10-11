package com.migangqui.micronaut.example.service

import com.migangqui.micronaut.example.model.Account
import com.migangqui.micronaut.example.repository.AccountRepository
import io.micronaut.security.authentication.providers.PasswordEncoder
import io.reactivex.Single
import javax.inject.Singleton

interface
AccountService {
    fun createAccount(account: Account): Account
}

@Singleton
class AccountServiceImpl(private val accountRepository: AccountRepository, private val passwordEncoder: PasswordEncoder) : AccountService {
    override fun createAccount(account: Account): Account {
        account.authorities = mutableListOf("USER")
        account.password = passwordEncoder.encode(account.password)
        return accountRepository.create(account)
    }
}