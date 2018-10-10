package com.migangqui.micronaut.example.security

import com.migangqui.micronaut.example.repository.AccountRepository
import io.micronaut.security.authentication.providers.UserFetcher
import io.micronaut.security.authentication.providers.UserState
import io.reactivex.Flowable
import mu.KotlinLogging
import org.reactivestreams.Publisher
import java.nio.file.attribute.UserPrincipalNotFoundException
import javax.inject.Singleton

@Singleton
class CustomUserFetcher(private val accountRepository: AccountRepository) : UserFetcher {

    private val log = KotlinLogging.logger {}

    override fun findByUsername(username: String): Publisher<UserState> {
        log.info { "Authenticating $username" }
        val principal = accountRepository.getByUsername(username)
        return when (principal) {
            null -> throw UserPrincipalNotFoundException("User $username not found")
            else -> Flowable.just(CustomUserState(principal))
        }
    }
}