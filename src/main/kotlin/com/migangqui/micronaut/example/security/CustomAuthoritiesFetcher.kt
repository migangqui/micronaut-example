package com.migangqui.micronaut.example.security

import com.migangqui.micronaut.example.repository.AccountRepository
import io.micronaut.security.authentication.providers.AuthoritiesFetcher
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Singleton

@Singleton
class CustomAuthoritiesFetcher(private val accountRepository: AccountRepository): AuthoritiesFetcher {
    override fun findAuthoritiesByUsername(username: String): Publisher<MutableList<String>> {
        val principal = accountRepository.getByUsername(username)
        return if (principal != null) Flowable.just(principal.authorities) else Flowable.just(mutableListOf())
    }
}