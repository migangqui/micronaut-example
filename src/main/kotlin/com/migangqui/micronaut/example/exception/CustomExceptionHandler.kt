package com.migangqui.micronaut.example.exception

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import javax.inject.Singleton

@Singleton
class CustomExceptionHandler: ExceptionHandler<Exception, HttpResponse<String>> {
    override fun handle(p0: HttpRequest<*>?, p1: Exception?): HttpResponse<String> {
        return HttpResponse.serverError("Error")
    }
}