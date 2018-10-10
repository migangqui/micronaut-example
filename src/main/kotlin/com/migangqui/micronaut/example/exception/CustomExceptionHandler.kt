package com.migangqui.micronaut.example.exception

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import mu.KotlinLogging
import java.nio.file.attribute.UserPrincipalNotFoundException
import javax.inject.Singleton

@Singleton
class CustomExceptionHandler: ExceptionHandler<Exception, HttpResponse<String>> {

    private val log = KotlinLogging.logger {}

    override fun handle(p0: HttpRequest<*>?, exception: Exception?): HttpResponse<String> {
        log.error { "Error $exception" }

        return when (exception) {
            is UserPrincipalNotFoundException -> HttpResponse.unauthorized()
            else -> return HttpResponse.serverError("Error")
        }
    }
}