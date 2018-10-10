package com.migangqui.micronaut.example.security

import io.micronaut.security.authentication.providers.AuthoritiesFetcher
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Singleton

@Singleton
class CustomAuthoritiesFetcher: AuthoritiesFetcher {
    override fun findAuthoritiesByUsername(username: String?): Publisher<MutableList<String>> {
        return Flowable.just(mutableListOf("user"))
    }
}