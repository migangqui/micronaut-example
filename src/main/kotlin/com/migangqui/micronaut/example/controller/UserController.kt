package com.migangqui.micronaut.example.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.created
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import com.migangqui.micronaut.example.model.User
import com.migangqui.micronaut.example.service.UserService
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import javax.annotation.security.PermitAll

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/users")
class UserController(private val userService: UserService) {

    @Get
    fun getUsers(): HttpResponse<List<User>> {
        return userService.getUsers()
    }

    @Get("/{userId}")
    fun getUser(userId: String): HttpResponse<User> {
        return userService.getUserById(userId)
    }

    @Post
    fun createUser(@Body user: User): HttpResponse<User> {
       return userService.createUser(user)
    }
}