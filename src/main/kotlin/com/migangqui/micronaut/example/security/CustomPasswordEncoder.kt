package com.migangqui.micronaut.example.security

import io.micronaut.security.authentication.providers.PasswordEncoder
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Singleton

@Singleton
class CustomPasswordEncoder: PasswordEncoder {

    override fun encode(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }
    override fun matches(candidate: String?, original: String?): Boolean {
        return BCrypt.checkpw(candidate, original)
    }
}