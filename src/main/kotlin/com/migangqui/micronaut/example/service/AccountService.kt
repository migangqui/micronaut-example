package com.migangqui.micronaut.example.service

import com.migangqui.micronaut.example.model.Account
import com.migangqui.micronaut.example.repository.AccountRepository
import io.reactivex.Single
import javax.inject.Singleton

interface
AccountService {
    fun createAccount(account: Account): Account
}

@Singleton
class AccountServiceImpl(private val accountRepository: AccountRepository) : AccountService {
    override fun createAccount(account: Account): Account {
        account.authorities = mutableListOf("USER")
        return accountRepository.create(account)
    }
}