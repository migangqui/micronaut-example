package com.migangqui.micronaut.example.service

import com.migangqui.micronaut.example.model.Account
import com.migangqui.micronaut.example.repository.AccountRepository
import io.reactivex.Single
import javax.inject.Singleton

interface AccountService {
    fun createAccount(account: Account): Single<Account>
}

@Singleton
class AccountServiceImpl(private val accountRepository: AccountRepository) : AccountService {
    override fun createAccount(account: Account): Single<Account> {
        return accountRepository.create(account)
    }
}