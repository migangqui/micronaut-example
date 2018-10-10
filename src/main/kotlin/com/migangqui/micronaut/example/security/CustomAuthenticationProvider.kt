package com.migangqui.micronaut.example.security

import com.migangqui.micronaut.example.repository.UserRepository
import java.util.Collections.emptyList
import io.reactivex.Flowable
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import org.reactivestreams.Publisher
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.UserDetails
import javax.inject.Singleton


@Singleton
class CustomAuthenticationProvider(private val userRepository: UserRepository) : AuthenticationProvider {
    override fun authenticate(authenticationRequest: AuthenticationRequest<*, *>): Publisher<AuthenticationResponse> {
        return Flowable.just(UserDetails("user", listOf("user")))
    }
}