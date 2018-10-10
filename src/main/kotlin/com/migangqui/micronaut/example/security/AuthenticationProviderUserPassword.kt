package com.migangqui.micronaut.example.security

import io.micronaut.security.authentication.AuthenticationFailed
import io.reactivex.Flowable
import java.util.ArrayList
import io.micronaut.security.authentication.UserDetails
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import org.reactivestreams.Publisher
import io.micronaut.security.authentication.AuthenticationProvider
import javax.inject.Singleton


@Singleton
class AuthenticationProviderUserPassword : AuthenticationProvider {
    override fun authenticate(authenticationRequest: AuthenticationRequest<*, *>): Publisher<AuthenticationResponse> {
        return if (authenticationRequest.identity == "user" && authenticationRequest.secret == "password") {
            Flowable.just(UserDetails("user", ArrayList()))
        } else Flowable.just(AuthenticationFailed())
    }
}