package com.migangqui.micronaut.example.security

import io.micronaut.security.authentication.providers.PasswordEncoder
import javax.inject.Singleton

@Singleton
class CustomPasswordEncoder: PasswordEncoder {
    override fun encode(password: String): String {
        return password
    }
    override fun matches(p0: String?, p1: String?): Boolean {
        return p0 == p1
    }
}