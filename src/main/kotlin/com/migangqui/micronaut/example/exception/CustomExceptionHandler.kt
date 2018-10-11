package com.migangqui.micronaut.example.exception

import com.migangqui.micronaut.example.logger.Log
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import java.nio.file.attribute.UserPrincipalNotFoundException
import javax.inject.Singleton

@Singleton
class CustomExceptionHandler: ExceptionHandler<Exception, HttpResponse<Any>> {

    override fun handle(p0: HttpRequest<*>?, exception: Exception?): HttpResponse<Any> {
        Log.error("Error $exception")
        return when (exception) {
            is UserPrincipalNotFoundException -> HttpResponse.unauthorized()
            else -> return HttpResponse.serverError("Error")
        }
    }
}