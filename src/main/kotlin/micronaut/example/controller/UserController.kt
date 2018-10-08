package micronaut.example.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.created
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import micronaut.example.model.User
import micronaut.example.service.UserService

@Controller("/api/users")
class UserController(private val userService: UserService) {

    @Get
    fun getUsers(): List<User> {
        return userService.getUsers()
    }

    @Get("/{userId}")
    fun getUser(userId: String): User {
        return userService.getUserById(userId)
    }

    @Post
    fun createUser(@Body user: User): HttpResponse<User> {
        return created(userService.createUser(user))
    }
}