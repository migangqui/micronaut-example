package com.migangqui.micronaut.example.controller

import com.migangqui.micronaut.example.model.User
import com.migangqui.micronaut.example.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.*
import io.reactivex.Single

@Controller("/api/users")
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