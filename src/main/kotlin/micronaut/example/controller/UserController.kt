package micronaut.example.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import micronaut.example.model.User
import micronaut.example.service.UserService
import org.bson.Document

@Controller("/api/users")
class UserController(private val userService: UserService) {

    @Get
    fun getUsers(): List<Document> {
        return userService.getUsers().blockingGet()
    }

    @Get("/{userId}")
    fun getUser(userId: String): User {
        return userService.getUserById("id")
    }
}