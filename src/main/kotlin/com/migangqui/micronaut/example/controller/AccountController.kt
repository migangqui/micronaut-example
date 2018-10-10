package com.migangqui.micronaut.example.controller

import com.migangqui.micronaut.example.model.Account
import com.migangqui.micronaut.example.service.AccountService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.reactivex.Single
import javax.annotation.security.PermitAll

@Controller("/api/accounts")
class AccountController(private val accountService: AccountService) {

    @Post
    @PermitAll
    fun create(account: Account) : Account {
        return accountService.createAccount(account)
    }
}