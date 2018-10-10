package com.migangqui.micronaut.example.controller

import com.migangqui.micronaut.example.model.User
import com.migangqui.micronaut.example.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.reactivex.Single

@Controller("/api/users")
@Secured(SecurityRule.IS_AUTHENTICATED)
//@PermitAll
class UserController(private val userService: UserService) {

    @Get
    fun getUsers(): HttpResponse<Single<List<User>>> {
        return ok(userService.getUsers())
    }

    @Get("/{userId}")
    fun getUser(userId: String): HttpResponse<Single<User>> {
        return ok(userService.getUserById(userId))
    }

    @Post
    fun createUser(@Body user: User): HttpResponse<Single<User>> {
        return created(userService.createUser(user))
    }

    @Delete
    fun deleteUsers(): HttpResponse<Unit> {
        userService.deleteUsers()
        return noContent()
    }

    @Delete("/{userId}")
    fun deleteUser(userId: String): HttpResponse<Unit> {
        userService.deleteUserById(userId)
        return noContent()
    }
}