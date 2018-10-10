package com.migangqui.micronaut.example.security

import com.migangqui.micronaut.example.model.Account
import io.micronaut.security.authentication.providers.UserState

class CustomUserState(private val account: Account): UserState {
    override fun isEnabled(): Boolean {
        return true
    }

    override fun isPasswordExpired(): Boolean {
        return false
    }

    override fun getUsername(): String {
        return account.username
    }

    override fun isAccountExpired(): Boolean {
        return false
    }

    override fun getPassword(): String {
        return account.password
    }

    override fun isAccountLocked(): Boolean {
        return false
    }
}